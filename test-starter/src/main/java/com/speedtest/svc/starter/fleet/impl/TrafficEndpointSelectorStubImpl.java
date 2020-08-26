package com.speedtest.svc.starter.fleet.impl;

import static java.util.Arrays.asList;

import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.speedtest.svc.starter.fleet.spi.TrafficEndpointSelectResult;
import com.speedtest.svc.starter.fleet.spi.TrafficEndpointSelector;

@Service
public class TrafficEndpointSelectorStubImpl implements TrafficEndpointSelector {

	@Override
	public TrafficEndpointSelectResult selectAvailableEndpoints(String remoteAddress) {

		return new TrafficEndpointSelectResult(new HashSet<>(asList("https://nginx:8080")));
	}
}
