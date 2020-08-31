package com.speedtest.svc.starter.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Response to a speed-test initiation request
 * containing the key/identifier of the speed test,
 * the end time (ms past UNIX epoch) and a list of
 * endpoints with corresponding expiring authorization tokens
 * 
 * @author atotev
 *
 */
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
