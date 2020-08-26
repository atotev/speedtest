package com.speedtest.svc.starter.fleet.spi;

import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TrafficEndpointSelectResult {

	private final Set<String> urls;
}
