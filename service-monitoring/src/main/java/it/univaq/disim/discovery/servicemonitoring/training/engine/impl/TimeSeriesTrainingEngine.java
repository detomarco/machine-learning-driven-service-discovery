package it.univaq.disim.discovery.servicemonitoring.training.engine.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.univaq.disim.discovery.servicemonitoring.common.Utils;
import it.univaq.disim.discovery.servicemonitoring.monitoring.model.MonitoringData;
import it.univaq.disim.discovery.servicemonitoring.monitoring.repository.MonitoringDataRepository;
import it.univaq.disim.discovery.servicemonitoring.training.engine.TrainingEngine;
import it.univaq.disim.discovery.servicemonitoring.training.model.TrainingData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service("time-series")
public class TimeSeriesTrainingEngine implements TrainingEngine {

    @Autowired
    private MonitoringDataRepository monitoringDataRepository;

    @Override
    public TrainingData generateModel() {
        List<MonitoringData> allMonitoringData = monitoringDataRepository.findAll();
        File inputFile = new File("temp/time-series-input.json");
        try {

            Utils.writeObjToFile(inputFile, allMonitoringData);

            String scriptPath = new File("ml-scripts/time-series-training.py").getPath();

            /* Create the ProcessBuilder */
            ProcessBuilder pb = new ProcessBuilder("python3", "-u", scriptPath, inputFile.getAbsolutePath());
            pb.redirectErrorStream(true);

            /* Start the process */
            Process proc = pb.start();

            /* Read the process's output */
            List<String> outputLines = Utils.toStrings(proc.getInputStream());
            /* Clean-up */
            proc.destroy();
            log.info(String.join("\n", outputLines));

            ObjectMapper jackson = new ObjectMapper();
            List<String> filesName = jackson.readValue(outputLines.get(outputLines.size() - 1), new TypeReference<List<String>>() {
            });

            // create input file
            List<String> serviceInstancesUrl = allMonitoringData.stream()
                    .map(MonitoringData::getServiceInstance)
                    .distinct()
                    .collect(Collectors.toList());

            ObjectMapper mapper = new ObjectMapper();
            List<String> inputNames = serviceInstancesUrl.stream()
                    .map(serviceInstance -> {
                        List<MonitoringData> monitoringData = monitoringDataRepository.findByServiceInstance(serviceInstance, PageRequest.of(0, 10));
                        try {
                            String monitoringDataJson = mapper.writeValueAsString(monitoringData);
                            String instanceSanitize = serviceInstance.substring(7).replaceAll(":", ".");
                            String fileName = "temp/" + instanceSanitize + ".input.json";
                            FileUtils.writeStringToFile(new File(fileName), monitoringDataJson, Charset.defaultCharset());

                            return fileName;
                        } catch (IOException e) {
                            log.error(e.getMessage(), e);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            filesName.addAll(inputNames);
            return new TrainingData(Utils.zip(filesName));

        } catch (IOException e) {
            throw new RuntimeException("Error during script calling", e);
        } finally {
            inputFile.delete();
        }
    }
}
