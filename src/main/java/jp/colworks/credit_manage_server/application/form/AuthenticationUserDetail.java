package jp.colworks.credit_manage_server.application.form;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

/**
 * ユーザー認証情報
 * 
 * @author col
 */
public class AuthenticationUserDetail implements UserDetails {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    @Setter
    @Getter
    private String userId;

    /**
     * ユーザーに付与された権限を返却する
     * 
     * @return 権限
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<GrantedAuthority>();
    }

    @Override
    public String getUsername() {
        // TODO: 一旦ユーザーIDを返却する
        return getUserId();
    }

    @Override
    public String getPassword() {
        // TODO ユーザ管理テーブルを作成して継承させて値はめ込むか？
        return "";
    }
}
