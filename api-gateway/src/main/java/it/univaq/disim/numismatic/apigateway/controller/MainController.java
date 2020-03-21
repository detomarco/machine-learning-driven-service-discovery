package it.univaq.disim.numismatic.apigateway.controller;

import com.google.common.io.CharStreams;
import it.univaq.disim.discovery.common.DiscoveryRestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/numismatic")
public class MainController {

    @Autowired
    private DiscoveryRestTemplate discoveryRestTemplate;

    private int queue;

    @RequestMapping(value = "/**")
    public ResponseEntity<?> routeGet(HttpServletRequest request, @RequestHeader HttpHeaders httpHeaders) throws IOException {
        queue++;
        String body = CharStreams.toString(request.getReader());
        String fullUrl = retrieveFullUrl(request);
        try {

            Object exchange = discoveryRestTemplate.exchange(
                    "numismatic-service",
                    fullUrl,
                    HttpMethod.valueOf(request.getMethod()),
                    body, httpHeaders, request.getParameterMap(),
                    Object.class
            );

            return ResponseEntity.ok(exchange);
        } catch (HttpClientErrorException e) {
            log.error(e.getLocalizedMessage(), e);
            return new ResponseEntity<>(e.getResponseBodyAsByteArray(), e.getResponseHeaders(), e.getStatusCode());
        } finally {
            queue--;
            log.info("Queue: " + queue);
        }

    }

    private String retrieveFullUrl(HttpServletRequest request) {

        String fullUrl = String.format("/%s", Arrays.stream(request.getRequestURI().split("/"))
                .skip(3)
                .collect(Collectors.joining("/")));

        if (request.getQueryString() != null) {
            fullUrl += "?" + request.getQueryString();
        }
        return fullUrl;
    }

}
