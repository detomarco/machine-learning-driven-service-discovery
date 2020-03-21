package it.univaq.disim.discovery.servicediscovery.prediction.engine.model;

import it.univaq.disim.discovery.servicediscovery.registry.model.FullServiceInstance;
import lombok.Data;

import java.util.List;

@Data
public class LinearRegressionPredictionRequest {

    private List<FullServiceInstance> serviceInstances;
}
