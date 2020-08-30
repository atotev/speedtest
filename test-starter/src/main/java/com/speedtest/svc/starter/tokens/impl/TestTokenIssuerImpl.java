package com.speedtest.svc.starter.tokens.impl;

import static java.lang.String.format;
import static org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString;
import static org.apache.commons.codec.digest.DigestUtils.sha256;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.speedtest.svc.starter.model.SpeedTestDescriptor;
import com.speedtest.svc.starter.model.TrafficEndpointForTest;
import com.speedtest.svc.starter.tokens.spi.TestTokenIssuer;

import lombok.val;

@Service
public class TestTokenIssuerImpl implements TestTokenIssuer {
	
	@Autowired
	private TestKeyGeneration testKeyGen;
	
	@Autowired
	private ExpirationTimeCalculation expirationTimeCalc;
	
	@Value("${hash.secret}")
	private String hashSecret;

	@Override
	public SpeedTestDescriptor issueEndpointTokens(List<String> endpoints) {
		
		val testKey = testKeyGen.generate();
		val testEndTimestamp = expirationTimeCalc.calculate();
		val tokens = endpoints.stream()
				.map(e -> new TrafficEndpointForTest(e, hash(testKey, testEndTimestamp, e)))
				.collect(Collectors.toList());
		return new SpeedTestDescriptor(testKey, testEndTimestamp, tokens);
	}
	
	private String hash(String testKey, long testEndTimestamp, String e) {
		return encodeBase64URLSafeString(sha256(format("%s,%s,%d,%s", hashSecret, testKey, testEndTimestamp, e)));
	}
}
