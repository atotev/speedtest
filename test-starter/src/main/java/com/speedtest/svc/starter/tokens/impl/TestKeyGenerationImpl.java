package com.speedtest.svc.starter.tokens.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
class TestKeyGenerationImpl implements TestKeyGeneration {

	@Override
	public String generate() {

		return UUID.randomUUID().toString();
	}
}
