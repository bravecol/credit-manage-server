package jp.colworks.credit_manage_server.config;

import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * X-Forwarded-ForによるIPアドレス検証クラス
 * 
 * @author col
 */
public class XForwardedForIpAddressMatcher implements RequestMatcher {

    private IpAddressMatcher ipAddressMatcher;

    public XForwardedForIpAddressMatcher(String ipAddress) {
        ipAddressMatcher = new IpAddressMatcher(ipAddress);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        // X-Forwarded-Forヘッダーより認証を通す特定IPを設定
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (!StringUtils.hasText(xForwardedFor)) {
            String[] array = xForwardedFor.split(",");
            // X-Forwarded-Forヘッダーは経由するたびに付与
            // 必ず認証できるように対応（偽装防止）
            return ipAddressMatcher.matches(array[array.length - 1].trim());
        }
        return false;
    }
}