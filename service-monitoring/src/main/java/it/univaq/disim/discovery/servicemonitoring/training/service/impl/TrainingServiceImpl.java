package it.univaq.disim.discovery.servicemonitoring.training.service.impl;

import it.univaq.disim.discovery.servicemonitoring.common.DiscoveryRestTemplate;
import it.univaq.disim.discovery.servicemonitoring.training.model.TrainingData;
import it.univaq.disim.discovery.servicemonitoring.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private DiscoveryRestTemplate discoveryRestTemplate;

    @Value("${discovery.address.ml-engine}")
    private List<String> mlEngineAddresses;

    @Value("${discovery.strategy.prediction}")
    private String predictionStrategy;

    @Override
    public void pushModelToDiscovery(TrainingData trainingData) {
        if (trainingData.getTrainingData() != null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
            mlEngineAddresses.forEach(
                    mlEngineAddress ->
                            discoveryRestTemplate.exchange(mlEngineAddress + "/model/" + predictionStrategy, HttpMethod.POST, trainingData.getTrainingData(), httpHeaders, null)
            );
        }
    }
}
