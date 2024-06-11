package com.barbosa.ms.productintegrationservice.productintegrationservice.config;

import feign.Client;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public Client feignClient() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        return new ApacheHttpClient(httpClient);
    }
}
