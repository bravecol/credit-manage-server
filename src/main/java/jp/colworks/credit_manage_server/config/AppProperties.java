package jp.colworks.credit_manage_server.config;

import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "access")
public class AppProperties {

    /** 認証許可するURL定義読込 */
    private Map<Integer, String> whiteList;
}
