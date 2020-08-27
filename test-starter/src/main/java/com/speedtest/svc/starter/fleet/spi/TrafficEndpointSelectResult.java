package com.speedtest.svc.starter.fleet.spi;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrafficEndpointSelectResult {

	private Set<String> urls;
}
