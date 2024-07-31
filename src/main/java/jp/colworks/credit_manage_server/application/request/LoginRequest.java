package jp.colworks.credit_manage_server.application.request;

import lombok.Data;

@Data
public class LoginRequest {

    /** ユーザーID */
    private String loginId;
    /** パスワード */
    private String password;
}
