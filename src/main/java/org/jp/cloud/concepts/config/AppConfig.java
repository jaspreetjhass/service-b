package org.jp.cloud.concepts.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "external")
public class AppConfig {

	private Interface interfaceC;
	private Interface interfaceD;

	@Data
	public static class Interface {
		private String baseUrl;
		private String requestUrl;
	}

}
