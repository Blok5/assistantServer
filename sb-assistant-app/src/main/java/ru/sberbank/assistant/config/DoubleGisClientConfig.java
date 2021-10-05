package ru.sberbank.assistant.config;


import doublegis.client.DoubleGisClient;
import doublegis.client.impl.DoubleGisClientImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DoubleGisClientConfig {

    @Bean
    RestTemplate doubleGisRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    DoubleGisClient doubleGisClient(
        @Value("${sb.assistant.2gis.url}") String url,
        @Value("${sb.assistant.2gis.key}") String key,
        @Qualifier("doubleGisRestTemplate") RestTemplate doubleGisRestTemplate
    ) { return new DoubleGisClientImpl(url, key, doubleGisRestTemplate); }
}
