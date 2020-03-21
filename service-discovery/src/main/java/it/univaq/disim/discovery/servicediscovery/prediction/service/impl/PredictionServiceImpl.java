package it.univaq.disim.discovery.servicediscovery.prediction.service.impl;

import it.univaq.disim.discovery.servicediscovery.prediction.engine.PredictionEngine;
import it.univaq.disim.discovery.servicediscovery.prediction.engine.model.MLEngineResponse;
import it.univaq.disim.discovery.servicediscovery.prediction.service.DiscoveryRestTemplate;
import it.univaq.disim.discovery.servicediscovery.prediction.service.PredictionService;
import it.univaq.disim.discovery.servicediscovery.registry.model.RankedServiceInstance;
import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PredictionServiceImpl implements PredictionService {

    @Value("${discovery.strategy.prediction}")
    private String predictionStrategy;

    @Value("${discovery.address.ml-engine}")
    private List<String> mlEngineAddresses;

    @Autowired
    private DiscoveryRestTemplate discoveryRestTemplate;

    private PredictionEngine mlEngineService;


    @Autowired
    public void setMlEngineService(ApplicationContext context) {
        mlEngineService = (PredictionEngine) context.getBean(predictionStrategy);
    }

    @Override
    public List<RankedServiceInstance> predictValues(String serviceType, List<ServiceInstance> serviceInstances, String clientContext) {
        return mlEngineService.predictValues(serviceType, serviceInstances, clientContext);
    }

    @Override
    public <T> MLEngineResponse callMLEngine(String serviceType, String clientContext, T request) {
        List<String> urls = mlEngineAddresses.stream()
                .map(mlEngineAddress ->  String.format("%s/predict/%s/%s/%s", mlEngineAddress, predictionStrategy, serviceType, clientContext))
                .collect(Collectors.toList());

        return discoveryRestTemplate.exchangeMultipleUrl(urls, HttpMethod.POST, request, MLEngineResponse.class);
    }

}
