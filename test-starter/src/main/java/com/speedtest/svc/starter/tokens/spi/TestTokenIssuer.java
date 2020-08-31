package com.speedtest.svc.starter.tokens.spi;

import java.util.List;

import com.speedtest.svc.starter.model.SpeedTestDescriptor;

/**
 * A service responsible for generating a token which the speed test client
 * is passing to the traffic end-points. The traffic endpoints are capable of
 * validating the token which is passed with each reques.
 * 
 * @author atotev
 *
 */
public interface TestTokenIssuer {

	SpeedTestDescriptor issueEndpointTokens(List<String> endpoints);
}
