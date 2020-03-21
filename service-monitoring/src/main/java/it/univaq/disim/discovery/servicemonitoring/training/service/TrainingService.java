package it.univaq.disim.discovery.servicemonitoring.training.service;


import it.univaq.disim.discovery.servicemonitoring.training.model.TrainingData;

public interface TrainingService {

    void pushModelToDiscovery(TrainingData trainingData);

}
