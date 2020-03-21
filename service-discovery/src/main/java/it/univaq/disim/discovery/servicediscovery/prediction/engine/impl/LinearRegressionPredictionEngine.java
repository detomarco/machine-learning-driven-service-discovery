package it.univaq.disim.discovery.servicediscovery.prediction.engine.impl;

import it.univaq.disim.discovery.servicediscovery.prediction.engine.PredictionEngine;
import it.univaq.disim.discovery.servicediscovery.prediction.engine.model.LinearRegressionPredictionRequest;
import it.univaq.disim.discovery.servicediscovery.prediction.engine.model.MLEngineResponse;
import it.univaq.disim.discovery.servicediscovery.prediction.service.PredictionService;
import it.univaq.disim.discovery.servicediscovery.registry.model.FullServiceInstance;
import it.univaq.disim.discovery.servicediscovery.registry.model.RankedServiceInstance;
import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("linear-regression")
public class LinearRegressionPredictionEngine implements PredictionEngine {

    @Autowired
    private PredictionService predictionService;

    @Override
    public List<RankedServiceInstance> predictValues(String serviceType, List<ServiceInstance> serviceInstances, String clientContext) {
        long start = System.currentTimeMillis();
        log.info("Start prediction");

        List<FullServiceInstance> fullServiceInstances = serviceInstances.stream()
                .map(serviceInstance -> {
                    FullServiceInstance fullServiceInstance = new FullServiceInstance();
                    fullServiceInstance.setName(serviceInstance.getName());
                    fullServiceInstance.setUrl(serviceInstance.getUrl());
                    fullServiceInstance.setMeanResponseTime(serviceInstance.getMeanResponseTime());
                    fullServiceInstance.setServiceContext(serviceInstance.getContext());
                    fullServiceInstance.setClientContext(clientContext);
                    fullServiceInstance.setTimestamp(new Date().getTime());
                    return fullServiceInstance;
                }).collect(Collectors.toList());

        LinearRegressionPredictionRequest request = new LinearRegressionPredictionRequest();
        request.setServiceInstances(fullServiceInstances);

        MLEngineResponse response = predictionService.callMLEngine(serviceType, clientContext, request);

        List<RankedServiceInstance> rankedServiceInstances = new ArrayList<>();
        for (int i = 0; i < serviceInstances.size(); i++) {
            RankedServiceInstance rankedServiceInstance = new RankedServiceInstance();
            rankedServiceInstance.setName(serviceInstances.get(i).getName());
            rankedServiceInstance.setContext(serviceInstances.get(i).getContext());
            rankedServiceInstance.setUrl(serviceInstances.get(i).getUrl());
            rankedServiceInstance.setRanking(response.getValues().get(i));
            rankedServiceInstances.add(rankedServiceInstance);
        }

        log.info("End prediction {}", System.currentTimeMillis() - start);
        return rankedServiceInstances;
    }

}
