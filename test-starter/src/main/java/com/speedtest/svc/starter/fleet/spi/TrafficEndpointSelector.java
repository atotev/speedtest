package com.speedtest.svc.starter.fleet.spi;

public interface TrafficEndpointSelector {
	
	TrafficEndpointSelectResult selectAvailableEndpoints(String remoteAddress);
}
