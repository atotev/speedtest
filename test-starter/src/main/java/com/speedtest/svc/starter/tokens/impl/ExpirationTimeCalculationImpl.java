package com.speedtest.svc.starter.tokens.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import static java.lang.System.currentTimeMillis;

/**
 * Default implementation of {@code ExpirationTimeCalculation} which
 * calculates expiration time based on pre-configured test duration
 * @author atotev
 *
 */
@Component
class ExpirationTimeCalculationImpl implements ExpirationTimeCalculation {

	@Value("${test.max.duration.ms}")
	private long testMaxDurationMs;

	@Override
	public Long calculate() {
		return currentTimeMillis() + testMaxDurationMs;
	}
}
