package com.speedtest.svc.starter.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SpeedTestDescriptor {

	private String testKey;
	private Long testEndTime;
	private List<TrafficEndpointForTest> endpoints;
}
