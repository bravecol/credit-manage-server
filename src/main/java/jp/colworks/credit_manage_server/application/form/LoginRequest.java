package jp.colworks.credit_manage_server.application.form;

import lombok.Data;

@Data
public class LoginRequest {

    /** ユーザーID */
    private String loginId;
    /** パスワード */
    private String password;
}
