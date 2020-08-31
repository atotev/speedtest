package com.speedtest.svc.starter.tokens.impl;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;

import com.speedtest.svc.starter.model.SpeedTestDescriptor;
import com.speedtest.svc.starter.model.TrafficEndpointForTest;

import lombok.val;

@SpringBootTest(properties="hash.secret=SECREET123")
@ContextHierarchy(@ContextConfiguration)
public class TestTokenIssuerImplTest {
	
	@Autowired
	private TestTokenIssuerImpl underTest;
	
	@Autowired
	private TestKeyGeneration testKeyGenMock;
	
	@Autowired
	private ExpirationTimeCalculation expirationTimeCalc;
	
	@Test
	public void testIssueEndpointTokens1() {
		
		val testKey = "uniquekey";
		when(testKeyGenMock.generate())
			.thenReturn(testKey);
		
		val expTimeMs = 1598821721776L;
		when(expirationTimeCalc.calculate())
			.thenReturn(expTimeMs);
			
		val hostA = "http://a";
		val hostB = "https://b";
		val expectedTokens = asList( // tokens below are pre-calculated for the regression test
				new TrafficEndpointForTest(hostA, "_W5iVW31s9P93Z0BFbwrb0uyQ5IkKsZdgpgEkoC88ys"),
				new TrafficEndpointForTest(hostB, "M8NmNozJwDQA1AtZ_d8knU7Ukap2pFrp9M1U5UKZ99U"));
		val expectedDescriptor = new SpeedTestDescriptor(testKey, expTimeMs, expectedTokens);
		val actualDescriptor =
				underTest.issueEndpointTokens(asList(hostA, hostB));
		assertEquals(expectedDescriptor, actualDescriptor, "Speed test descriptors mismatch");
	}
	
    @Configuration
    static class Config {

        @Bean
        TestTokenIssuerImpl underTest() {
        	
            return new TestTokenIssuerImpl();
        }
        
        @Bean
        ExpirationTimeCalculation expirationTimeCalc() {
        	
        	return mock(ExpirationTimeCalculation.class);
        }
        
        @Bean
        TestKeyGeneration testKeyGen() {
        	
        	return mock(TestKeyGeneration.class);
        }
    }
}
