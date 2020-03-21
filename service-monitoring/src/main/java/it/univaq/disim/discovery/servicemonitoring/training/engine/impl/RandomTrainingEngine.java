package it.univaq.disim.discovery.servicemonitoring.training.engine.impl;

import it.univaq.disim.discovery.servicemonitoring.training.engine.TrainingEngine;
import it.univaq.disim.discovery.servicemonitoring.training.model.TrainingData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("random")
public class RandomTrainingEngine implements TrainingEngine {

    @Override
    public TrainingData generateModel() {
        return new TrainingData();
    }
}
