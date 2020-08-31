package com.speedtest.svc.starter.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.speedtest.svc.starter.fleet.spi.TrafficEndpointSelector;
import com.speedtest.svc.starter.model.SpeedTestDescriptor;
import com.speedtest.svc.starter.tokens.spi.TestTokenIssuer;

import io.micrometer.core.annotation.Timed;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of the equivalent of api.fast.com service
 * responsible for initiating a speed test and returning traffic
 * end points URLs plus expiring tokens allowing the client to perform speed test
 * 
 * @author atotev
 *
 */
@RestController
@Slf4j
public class SpeedTestStarterController {
	
	@Autowired
	private TrafficEndpointSelector trafficEndpointSelector;
	
	@Autowired
	private TestTokenIssuer tokenIssuer;
	
	@RequestMapping(method=POST, path="/api/v1/test", produces = MediaType.APPLICATION_JSON)
	@Timed("speedtests.initialization")
	public SpeedTestDescriptor beginSpeedTest(HttpServletRequest request) {
		
		val remoteAddr = request.getRemoteAddr();
		log.debug("Requested new test from address: {}", remoteAddr);
		val endpoints = trafficEndpointSelector.selectAvailableEndpoints(remoteAddr);
		return tokenIssuer.issueEndpointTokens(endpoints.getUrls());
	}
}
