package com.speedtest.svc.starter.tokens.spi;

import java.util.List;
import java.util.Set;

import com.speedtest.svc.starter.model.TrafficEndpointForTest;

public interface TestTokenIssuer {

	List<TrafficEndpointForTest> issueEndpointTokens(String testKey, long testEndTimestamp, Set<String> endpoints);
}
