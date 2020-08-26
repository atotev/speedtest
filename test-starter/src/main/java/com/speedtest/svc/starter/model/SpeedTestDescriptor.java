package com.speedtest.svc.starter.model;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SpeedTestDescriptor {

	private final String testKey;
	private final Long testEndTime;
	private final List<TrafficEndpointForTest> endpoints;
}
