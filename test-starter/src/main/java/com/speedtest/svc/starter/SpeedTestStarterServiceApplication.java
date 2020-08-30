package com.speedtest.svc.starter;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;

@SpringBootApplication
public class SpeedTestStarterServiceApplication {

	@Autowired
    private CloseableHttpClient httpClient;
	
	@Bean
	public RestTemplate restTemplate() {

		return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
	}
	
	@Bean
	public TimedAspect timedAspect(MeterRegistry registry) {

		return new TimedAspect(registry);
	}
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpeedTestStarterServiceApplication.class, args);
	}
}
