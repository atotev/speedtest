package com.speedtest.svc.starter.fleet.spi;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Represents a selection of traffic endpoints
 * available for use during a speed test
 * 
 * @author atotev
 *
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TrafficEndpointSelectResult {

	private List<String> urls;
}
