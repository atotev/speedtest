package com.speedtest.svc.starter.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TrafficEndpointForTest {

	private final String url;
	private final String token;
}
