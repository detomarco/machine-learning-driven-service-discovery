package it.univaq.disim.discovery.servicemonitoring.training.engine.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.univaq.disim.discovery.servicemonitoring.common.Utils;
import it.univaq.disim.discovery.servicemonitoring.monitoring.model.MonitoringData;
import it.univaq.disim.discovery.servicemonitoring.monitoring.repository.MonitoringDataRepository;
import it.univaq.disim.discovery.servicemonitoring.training.engine.TrainingEngine;
import it.univaq.disim.discovery.servicemonitoring.training.model.TrainingData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service("linear-regression")
public class LinearRegressionTrainingEngine implements TrainingEngine {

    @Autowired
    private MonitoringDataRepository monitoringDataRepository;

    @Override
    public TrainingData generateModel() {
        List<MonitoringData> monitoringData = monitoringDataRepository.findAll();

        File inputFile = new File("temp/linear_regression_input.json");

        try {

            Utils.writeObjToFile(inputFile, monitoringData);

            String scriptPath = new File("ml-scripts/linear-regression-training.py").getPath();

            /* Create the ProcessBuilder */
            ProcessBuilder pb = new ProcessBuilder("python3", "-u", scriptPath, inputFile.getAbsolutePath());
            pb.redirectErrorStream(true);

            /* Start the process */
            Process proc = pb.start();

            /* Read the process's output */
            List<String> output = Utils.toStrings(proc.getInputStream());
            /* Clean-up */
            proc.destroy();
            log.info(String.join("\n", output));

            ObjectMapper jackson = new ObjectMapper();
            List<String> filesName = jackson.readValue(output.get(output.size() - 1), new TypeReference<List<String>>() {
            });

            return new TrainingData(Utils.zip(filesName));

        } catch (IOException e) {
            throw new RuntimeException("Error during script calling", e);
        } finally {
            inputFile.delete();
        }
    }

}
