package it.univaq.disim.discovery.servicemonitoring.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class DiscoveryRestTemplate {

    public <T, R> R exchange(String url, HttpMethod method, T body, HttpHeaders httpHeaders, Class<R> responseClass) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<R> exchange = restTemplate.exchange(url,
                method,
                new HttpEntity<>(body, httpHeaders),
                responseClass
        );
        return exchange.getBody();
    }

}
