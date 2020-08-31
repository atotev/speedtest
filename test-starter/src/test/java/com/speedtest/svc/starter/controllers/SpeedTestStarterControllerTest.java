package com.speedtest.svc.starter.controllers;

import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;

import com.speedtest.svc.starter.fleet.spi.TrafficEndpointSelectResult;
import com.speedtest.svc.starter.fleet.spi.TrafficEndpointSelector;
import com.speedtest.svc.starter.model.SpeedTestDescriptor;
import com.speedtest.svc.starter.tokens.spi.TestTokenIssuer;

import lombok.val;

@SpringBootTest(properties="hash.secret=SECREET123")
@ContextHierarchy(@ContextConfiguration)
public class SpeedTestStarterControllerTest {

	@Autowired
	private SpeedTestStarterController underTest;
	
	@Autowired
	private TrafficEndpointSelector endpointSelectorMock;
	
	@Autowired
	private TestTokenIssuer tokenIssuerMock;
	
	@Autowired
	private HttpServletRequest requestMock;

	@Test
	public void testBeginSpeedTest() {

		val testRemoteAddr = "1.2.3.4";
		when(requestMock.getRemoteAddr())
			.thenReturn(testRemoteAddr);
		
		val hostA = "http://a";
		val hostB = "https://b";
		val testEndpoints = asList(hostA, hostB);
		when(endpointSelectorMock.selectAvailableEndpoints(eq(testRemoteAddr)))
			.thenReturn(new TrafficEndpointSelectResult(testEndpoints));
		
		val expectedDescriptor = new SpeedTestDescriptor("unique", currentTimeMillis(), emptyList());
		when(tokenIssuerMock.issueEndpointTokens(eq(testEndpoints)))
			.thenReturn(expectedDescriptor);
		
		val actualDescriptor = underTest.beginSpeedTest(requestMock);
		assertEquals(expectedDescriptor, actualDescriptor, "Speed test descriptor mismatch");
	}

	@Configuration
	static class Config {

		@Bean
		SpeedTestStarterController underTest() {

			return new SpeedTestStarterController();
		}

		@Bean
		TrafficEndpointSelector restTemplate() {

			return mock(TrafficEndpointSelector.class);
		}

		@Bean
		TestTokenIssuer testTokenIssuer() {

			return mock(TestTokenIssuer.class);
		}
		
		@Bean
		HttpServletRequest httpServletRequest() {
			
			return mock(HttpServletRequest.class);
		}
	}
}
