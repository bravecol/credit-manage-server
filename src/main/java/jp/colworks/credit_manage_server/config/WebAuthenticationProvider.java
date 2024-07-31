package jp.colworks.credit_manage_server.config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import jp.colworks.credit_manage_server.application.request.AuthenticationUserDetail;
import jp.colworks.credit_manage_server.domain.model.LoginModel;
import jp.colworks.credit_manage_server.domain.service.LoginService;
import lombok.extern.slf4j.Slf4j;

/**
 * ユーザ認証処理を実施するクラス
 * 
 * @author col
 */
@Slf4j
@Component
public class WebAuthenticationProvider implements AuthenticationProvider {

    /** ログイン機能のサービスクラス */
    @Autowired
    private LoginService service;

    // TODO: 暫定的に認証を通すための固定のユーザーデータ DB設定までできたら削除
    private String temporaryLoginId = "manage";
    private String temporaryPassword = "passmanage";

    /**
     * 認証処理
     * 
     * @param authentication 認証リクエストオブジェクト
     * @throws AuthenticationException 認証に失敗した場合
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        AuthenticationUserDetail userDetail = (AuthenticationUserDetail) authentication.getPrincipal();
        String loginId = (String) userDetail.getUserId();
        String password = (String) authentication.getCredentials();
        LoginModel request = new LoginModel();
        request.setUsername(loginId);
        request.setPassword(password);

        log.info("認証処理開始");

        // ログインIDとパスワードが固定値と一致するかチェック（実際の認証ロジックをコメントアウト）
        if (!temporaryLoginId.equals(loginId) || !temporaryPassword.equals(password)) {
            throw new AuthenticationCredentialsNotFoundException("Invalid login ID or password.");
        }
        // 認証を通すための固定の権限設定
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        // UsernamePasswordAuthenticationTokenを作成して返す
        return new UsernamePasswordAuthenticationToken(userDetail, password, authorities);

        // TODO: DBや認証処理完成させたらコメントアウト
        // try {
        //     // ログイン処理を実施
        //     // FIXME: JWT認証かPasswordEncoderを設定予定 テーブル定義も必要 loginServiceにて対応
        //     service.authenticate(request);
        // } catch (Exception e) {
        //     // FIXME: loginServiceにて認証失敗時に専用の例外クラスも作成した方がいいかも
        //     throw new AuthenticationCredentialsNotFoundException(e.getMessage());
        // }

        // return new UsernamePasswordAuthenticationToken(userDetail, authentication.getCredentials(),
        //         userDetail.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }

}
