package org.jp.cloud.concepts.rest;

import org.jp.cloud.concepts.service.ServiceResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("serviceB")
public class ServiceBController {

	@Autowired
	private ServiceResponseHandler serviceResponseHandler;

	@GetMapping("processRequest")
	public String processRequest() {
		log.info("controller B executing ________________________.");
		return serviceResponseHandler.fetchResponseFromB();
	}
}
