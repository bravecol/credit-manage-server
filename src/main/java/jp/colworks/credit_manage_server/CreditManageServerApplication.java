package jp.colworks.credit_manage_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CreditManageServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditManageServerApplication.class, args);
	}

}
