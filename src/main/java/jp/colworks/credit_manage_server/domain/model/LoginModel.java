package jp.colworks.credit_manage_server.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginModel {

    /** ユーザー名 */
    private String username;
    /** パスワード */
    private String password;
}
