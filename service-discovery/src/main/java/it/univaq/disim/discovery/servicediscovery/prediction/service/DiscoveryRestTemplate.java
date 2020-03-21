package it.univaq.disim.discovery.servicediscovery.prediction.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class DiscoveryRestTemplate {

    public <T, R> R post(String url, @Nullable T request, Class<R> responseClass) {
        return exchange(url, HttpMethod.POST, request, responseClass);
    }

    public <R> R get(String url, Class<R> responseClass) {
        return exchange(url, HttpMethod.GET, null, responseClass);
    }

    public <T, R> R exchange(String url, HttpMethod method, T body, Class<R> responseClass) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<R> exchange = restTemplate.exchange(url,
                method,
                new HttpEntity<>(body),
                responseClass
        );
        return exchange.getBody();
    }

    public <T, R> R exchangeMultipleUrl(List<String> urls, HttpMethod method, T body, Class<R> responseType) {
        List<String> shuffledUrlList = new ArrayList<>(urls);
        Collections.shuffle(shuffledUrlList);

        Boolean success = Boolean.FALSE;
        R result = null;
        RuntimeException ex = null;
        for (String url : shuffledUrlList) {
            try {
                result = this.exchange(url, method, body, responseType);
                success = Boolean.TRUE;
                break;
            } catch (RestClientException e) {
                log.error("Endpoint {} not available", url);
                ex = e;
            }
        }

        if (success.equals(Boolean.FALSE) && ex != null) {
            throw ex;
        }

        return result;
    }

}
