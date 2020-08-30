package com.speedtest.svc.starter.tokens.spi;

import java.util.List;

import com.speedtest.svc.starter.model.SpeedTestDescriptor;

public interface TestTokenIssuer {

	SpeedTestDescriptor issueEndpointTokens(List<String> endpoints);
}
