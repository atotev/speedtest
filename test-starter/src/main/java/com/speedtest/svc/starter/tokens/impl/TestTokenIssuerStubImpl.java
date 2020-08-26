package com.speedtest.svc.starter.tokens.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.speedtest.svc.starter.model.TrafficEndpointForTest;
import com.speedtest.svc.starter.tokens.spi.TestTokenIssuer;

@Service
public class TestTokenIssuerStubImpl implements TestTokenIssuer {

	@Override
	public List<TrafficEndpointForTest> issueEndpointTokens(String testKey, long testEndTimestamp, Set<String> endpoints) {
		return endpoints.stream().map(e
				-> new TrafficEndpointForTest(e, "sig:" + testKey + testEndTimestamp)).collect(Collectors.toList());
	}
}
