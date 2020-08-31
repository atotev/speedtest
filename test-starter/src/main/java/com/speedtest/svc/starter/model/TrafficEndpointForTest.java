package com.speedtest.svc.starter.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A single traffic endpoint with a corresponding
 * authorization token that is valid for the duration of the speed test
 * 
 * @author atotev
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TrafficEndpointForTest {

	private String url;
	private String token;
}
