package jp.colworks.credit_manage_server.application.form;

import lombok.Data;

@Data
public class LoginForm {

    /** ユーザー名 */
    private String username;
    /** パスワード */
    private String password;
}
