package org.jp.cloud.concepts.service;

import java.net.URI;

import org.jp.cloud.concepts.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@DefaultProperties(defaultFallback = "defaultResponse2")
public class ServiceResponseHandler {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private AppConfig appConfig;

	@HystrixCommand(fallbackMethod = "defaultResponse")
	public String fetchResponseFromB() {
		log.info("calling serviceB +++++++++++++++++");
		final ResponseEntity<String> responseEntityC = restTemplate
				.getForEntity(URI.create(appConfig.getInterfaceC().getRequestUrl()), String.class);
		final ResponseEntity<String> responseEntityD = restTemplate
				.getForEntity(URI.create(appConfig.getInterfaceD().getRequestUrl()), String.class);
		log.info("response received from serviceB +++++++++++++++++");
		/*
		 * fallback method can be executed for any exception. But fallback execution can
		 * be avoided for all exception added to ignoreException property
		 * inside @HystrixCommand
		 * 
		 */
		throw new NullPointerException();
		// return responseEntityC.getBody().concat(responseEntityD.getBody());
	}

	/*
	 * fallback method specified inside @HystrixCommand has more preference than
	 * fallback method mentioned in @DefaultProperties
	 */
	public static String defaultResponse() {
		log.info("preparing default response %%%%%%%%%%%%%%%%%%%%%%%%%");
		return "Hello from B";
	}

	public static String defaultResponse2() {
		log.info("preparing default response %%%%%%%%%%%%%%%%%%%%%%%%%");
		return "Hello from B";
	}
}
