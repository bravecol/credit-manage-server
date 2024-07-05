package jp.colworks.credit_manage_server.domain.model;

import lombok.Data;

@Data
public class LoginModel {

    /** ユーザー名 */
    private String username;
    /** パスワード */
    private String password;
}
