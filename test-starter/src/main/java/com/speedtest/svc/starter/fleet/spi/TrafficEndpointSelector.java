package com.speedtest.svc.starter.fleet.spi;

/**
 * A service interface that is
 * responsible for selecting (based on proximity and available capacity)
 * a list of traffic end-points for client to call during speed test
 * 
 * @author atotev
 *
 */
public interface TrafficEndpointSelector {
	
	TrafficEndpointSelectResult selectAvailableEndpoints(String remoteAddress);
}
