package com.speedtest.svc.starter.tokens.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

/**
 * Default implementation of @code {@link TestKeyGeneration}
 * which generates a UUID returns its string representation
 * 
 * @author atotev
 *
 */
@Component
class TestKeyGenerationImpl implements TestKeyGeneration {

	@Override
	public String generate() {

		return UUID.randomUUID().toString();
	}
}
