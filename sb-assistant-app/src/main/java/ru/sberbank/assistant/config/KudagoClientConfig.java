package ru.sberbank.assistant.config;


import com.sb.api.kudago.client.KudagoClient;
import com.sb.api.kudago.client.impl.KudagoClientImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class KudagoClientConfig {

    @Bean
    RestTemplate kudagoRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    KudagoClient kudagoClient(
        @Value("${sb.assistant.kudago.url}") String url,
        @Qualifier("kudagoRestTemplate") RestTemplate kudagoRestTemplate
    ) { return new KudagoClientImpl(url, kudagoRestTemplate); }
}
