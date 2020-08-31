package com.speedtest.svc.starter;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shared HttpClient configuration.
 * Allows for configuring HTTP connection settings, pooling, etc
 * 
 * @author atotev
 *
 */
@Configuration
public class HttpClientConfig {
	
	@Value("${http.client.connect.timeout.ms}")
	private int connectTimeoutMs;
	
	@Value("${http.client.request.timeout.ms}")
	private int requestTimeoutMs;

	@Value("${http.client.socket.timeout.ms}")
	private int socketTimeoutMs;
	
    @Bean
    public CloseableHttpClient httpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(connectTimeoutMs)
                .setConnectTimeout(requestTimeoutMs)
                .setSocketTimeout(socketTimeoutMs).build();
 
        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
    }
}
