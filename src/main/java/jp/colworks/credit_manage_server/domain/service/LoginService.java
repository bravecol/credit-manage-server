package jp.colworks.credit_manage_server.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import jp.colworks.credit_manage_server.domain.model.LoginModel;
import lombok.extern.slf4j.Slf4j;

/**
 * 認証処理サービスクラス
 * 
 * @author col
 */
@Slf4j
@Service
public class LoginService {

    public String authenticate(LoginModel model) {

        // TODO: JWTなどで認証定義する
        String dummyToken = String.join("", List.of("a", "b"));

        return dummyToken;
    }
}
