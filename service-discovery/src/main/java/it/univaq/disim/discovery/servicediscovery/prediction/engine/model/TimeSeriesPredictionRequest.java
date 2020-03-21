package it.univaq.disim.discovery.servicediscovery.prediction.engine.model;

import lombok.Data;

import java.util.List;

@Data
public class TimeSeriesPredictionRequest {

    private List<String> inputFileNames;

}
