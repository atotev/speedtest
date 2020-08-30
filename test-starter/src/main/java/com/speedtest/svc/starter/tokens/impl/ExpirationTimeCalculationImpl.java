package com.speedtest.svc.starter.tokens.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import static java.lang.System.currentTimeMillis;

@Component
class ExpirationTimeCalculationImpl implements ExpirationTimeCalculation {

	@Value("${test.max.duration.ms}")
	private long testMaxDurationMs;

	@Override
	public Long calculate() {
		return currentTimeMillis() + testMaxDurationMs;
	}
}
