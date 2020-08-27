package com.speedtest.svc.starter.fleet.impl;

import java.net.URI;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.speedtest.svc.starter.fleet.spi.TrafficEndpointSelectResult;
import com.speedtest.svc.starter.fleet.spi.TrafficEndpointSelector;

@Service
public class TrafficEndpointSelectorImpl implements TrafficEndpointSelector {
	
	@Value("${traffic.endpoint.selector.url}")
	private URI selectorSvcUri;
	
	@Value("${traffic.endpoint.selector.key}")
	private String selectorSvcKey;

	@Value("${traffic.endpoint.selector.secret}")
	private String selectorSvcSecret;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostConstruct
	private void addBasicAuthInterceptor() {
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(selectorSvcKey, selectorSvcSecret));
	}

	@Override
	public TrafficEndpointSelectResult selectAvailableEndpoints(String remoteAddress) {
		
		return restTemplate.getForObject(selectorSvcUri, TrafficEndpointSelectResult.class);
	}
}
