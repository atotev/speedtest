package com.speedtest.svc.starter.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speedtest.svc.starter.fleet.spi.TrafficEndpointSelector;
import com.speedtest.svc.starter.model.SpeedTestDescriptor;
import com.speedtest.svc.starter.tokens.spi.TestTokenIssuer;

import lombok.val;

@RestController
public class SpeedTestStarterController {
	
	@Value("${test.max.duration.sec}")
	private long testMaxDurationSec;
	
	@Autowired
	private TrafficEndpointSelector trafficEndpointSelector;
	
	@Autowired
	private TestTokenIssuer tokenIssuer;
	
	@RequestMapping(method=POST, path="/api/v1/test", produces = MediaType.APPLICATION_JSON)
	public SpeedTestDescriptor beginTest(HttpServletRequest request) {
		
		val testKey = UUID.randomUUID().toString();
		val testEndTimestamp = System.currentTimeMillis() + testMaxDurationSec;
		val endpoints = trafficEndpointSelector.selectAvailableEndpoints(request.getRemoteAddr());
		return new SpeedTestDescriptor(testKey, testEndTimestamp,
				tokenIssuer.issueEndpointTokens(testKey, testEndTimestamp, endpoints.getUrls()));
	}
}
