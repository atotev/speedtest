package com.speedtest.svc.starter.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpeedTestDescriptor {

	private String testKey;
	private Long testEndTime;
	private List<TrafficEndpointForTest> endpoints;
}
