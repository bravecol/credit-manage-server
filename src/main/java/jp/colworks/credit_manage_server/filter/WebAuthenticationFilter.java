package jp.colworks.credit_manage_server.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jp.colworks.credit_manage_server.application.request.AuthenticationUserDetail;
import jp.colworks.credit_manage_server.application.request.LoginRequest;

/**
 * ユーザ認証用のフィルタークラス
 * 
 * <pre>
 * ログイン画面から受け取ったJSONパラメータで認証トークンを作成、後続の認証処理に渡す。
 * </pre>
 * 
 * @author col
 */
public class WebAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 認証処理
     * 
     * @param request  Httpリクエスト
     * @param response Httpレスポンス
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) {

        LoginRequest loginRequest = null;
        try {
            loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        AuthenticationUserDetail userDetail = new AuthenticationUserDetail();
        userDetail.setUserId(loginRequest.getLoginId());

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userDetail,
                loginRequest.getPassword());
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
