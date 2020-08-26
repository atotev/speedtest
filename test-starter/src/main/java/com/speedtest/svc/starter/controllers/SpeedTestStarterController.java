package com.speedtest.svc.starter.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speedtest.svc.starter.fleet.spi.TrafficEndpointSelectResult;
import com.speedtest.svc.starter.fleet.spi.TrafficEndpointSelector;
import com.speedtest.svc.starter.model.SpeedTestDescriptor;
import com.speedtest.svc.starter.tokens.spi.TestTokenIssuer;

@RestController
public class SpeedTestStarterController {
	
	@Autowired
	private TrafficEndpointSelector trafficEndpointSelector;
	
	@Autowired
	private TestTokenIssuer tokenIssuer;
	
	@RequestMapping(method=POST, path="/api/v1/test", produces = MediaType.APPLICATION_JSON)
	public SpeedTestDescriptor beginTest(HttpServletRequest request) {
		final TrafficEndpointSelectResult endpoints =
				trafficEndpointSelector.selectAvailableEndpoints(request.getRemoteAddr());
		final String testKey = UUID.randomUUID().toString();
		final long testEndTimestamp = System.currentTimeMillis() + 900000; // allow max 15 min
		return new SpeedTestDescriptor(testKey, testEndTimestamp,
				tokenIssuer.issueEndpointTokens(testKey, testEndTimestamp, endpoints.getUrls()));
	}
}
