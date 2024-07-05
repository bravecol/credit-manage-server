package jp.colworks.credit_manage_server.config;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.colworks.credit_manage_server.filter.WebAuthenticationFilter;

/**
 * 認証情報設定
 * 
 * @author col
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {

    /** 認証対象URL */
    private static final String PERMIT_URL = "/api/**";
    /** 認証対象URL */
    private static final String[] PERMIT_ALL = { "/api/healthcheck" };
    /** ログインURL */
    private static final String LOGIN_URL = "/api/login";
    /** ログアウトURL */
    private static final String LOGOUT_URL = "/api/logout";
    /** ログアウト時に削除するクッキー */
    private static final String[] LOGOUT_DELETE_COOKIES = { "JSESSIONID" };

    @Autowired
    private AppProperties properties;

    @Autowired
    private WebAuthenticationProvider authenticationProvider;

    /**
     * AuthenticationManagerBuilderを構成
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void configureProvider(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping(PERMIT_URL)
                .allowedOrigins("http://localhost:5173")
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name())
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    /**
     * HttpSecurityを構成
     * 
     * @param http HttpSecurity
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.securityMatcher(PERMIT_URL)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PERMIT_ALL).permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint))
                .logout(logout -> logout
                        .logoutUrl(LOGOUT_URL)
                        .invalidateHttpSession(true)
                        .deleteCookies(LOGOUT_DELETE_COOKIES)
                        .logoutSuccessHandler(logoutSuccessHandler()))
                .csrf((csrf) -> csrf.disable());

        // ログイン認証用のフィルタークラスを設定
        WebAuthenticationFilter authenticationFilter = new WebAuthenticationFilter();
        authenticationFilter
                .setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(LOGIN_URL, HttpMethod.POST.name()));
        authenticationFilter.setUsernameParameter("loginId");
        authenticationFilter.setPasswordParameter("password");
        authenticationFilter.setAuthenticationManager(
                authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)));
        authenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        authenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        authenticationFilter.setSecurityContextRepository(new HttpSessionSecurityContextRepository());

        http.addFilter(authenticationFilter);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 未認証時の処理
     * 
     * <pre>
     * HTTPステータス401を返す
     * </pre>
     */
    private AuthenticationEntryPoint authenticationEntryPoint = new AuthenticationEntryPoint() {

        /**
         * 認証スキームを開始
         * 
         * @param request
         * @param response
         * @param authException
         */
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                AuthenticationException authException) throws IOException, ServletException {

            if (response.isCommitted()) {
                return;
            }

            String message = "";
            if (request.getCookies() != null) {
                // クッキー情報を確認
                for (Cookie c : request.getCookies()) {
                    if ("JSESSIONID".equals(c.getName())) {
                        // TODO: メッセージは暫定
                        message = "タイムアウト";
                        break;
                    }
                }

                if (!StringUtils.hasLength(message)) {
                    // TODO: メッセージは暫定
                    message = "ログインが必要";
                }
            }

            response.setContentType("text/plane; charset=UTF-8;");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().append(message);
        }
    };

    /**
     * ログアウトした時の処理
     * 
     * <pre>
     * HTTPステータス200を返却
     * </pre>
     * 
     * @return LogoutSuccessHandler
     */
    private LogoutSuccessHandler logoutSuccessHandler() {
        return new HttpStatusReturningLogoutSuccessHandler();
    }

    /**
     * ログイン認証が成功した時の処理
     * 
     * <pre>
     * HTTPステータス200を返却
     * </pre>
     */
    private SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler = new SimpleUrlAuthenticationSuccessHandler() {

        /**
         * ユーザーが正常に認証されたときに呼び出される
         * 
         * @param request
         * @param response
         * @param auth
         * @throws IOException
         * @throws ServletException
         */
        @Override
        public void onAuthenticationSuccess(HttpServletRequest request,
                HttpServletResponse response,
                Authentication auth) throws IOException, ServletException {
            if (response.isCommitted()) {
                return;
            }
            response.setStatus(HttpStatus.OK.value());
            clearAuthenticationAttributes(request);
        }

    };
    /**
     * ログイン認証が失敗した時の処理
     * 認証失敗 HTTPステータス403を返す
     * LDAPのタイムアウト等接続エラー HTTPステータス408を返す
     */
    private SimpleUrlAuthenticationFailureHandler authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler() {
        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                AuthenticationException exception) throws IOException, ServletException {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
            return;
        }
    };
}
