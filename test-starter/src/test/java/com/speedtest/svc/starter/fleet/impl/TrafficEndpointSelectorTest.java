package com.speedtest.svc.starter.fleet.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.web.client.RestTemplate;

import com.speedtest.svc.starter.HttpClientConfig;
import com.speedtest.svc.starter.fleet.spi.TrafficEndpointSelectResult;

import lombok.val;
import static java.util.Arrays.asList;

@SpringBootTest(webEnvironment=WebEnvironment.NONE,
		properties="traffic.endpoint.selector.url=http://localhost:${stubrunner.runningstubs.endpoint-selector.port}/")
@AutoConfigureStubRunner(ids = {"com.speedtest.svc:endpoint-selector:0.0.1-SNAPSHOT:stubs"},
		stubsMode = StubRunnerProperties.StubsMode.LOCAL)
@ContextHierarchy({
		@ContextConfiguration(classes = HttpClientConfig.class),
		@ContextConfiguration()
})
public class TrafficEndpointSelectorTest {
	
	@Autowired
	private TrafficEndpointSelectorImpl underTest;

	@Test
	public void testCallEndpointSelector() {
		
		var expectedEndpoints = new TrafficEndpointSelectResult(asList("https://a")); // based on contract mock
		val actualEndpoints = underTest.selectAvailableEndpoints("1.2.3.4");
		assertEquals(expectedEndpoints, actualEndpoints);
	}

    @Configuration
    static class Config {
    	
    	@Autowired
        private CloseableHttpClient httpClient;

        @Bean
        TrafficEndpointSelectorImpl underTest() {
        	
            return new TrafficEndpointSelectorImpl();
        }
    	
    	@Bean
    	public RestTemplate restTemplate() {

    		return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
    	}
    }
}
