package it.univaq.disim.discovery.servicemonitoring.training.scheduler;

import it.univaq.disim.discovery.servicemonitoring.training.engine.TrainingEngine;
import it.univaq.disim.discovery.servicemonitoring.training.model.TrainingData;
import it.univaq.disim.discovery.servicemonitoring.training.service.TrainingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TrainingScheduler {

    @Autowired
    private TrainingService trainingService;

    @Value("${discovery.strategy.prediction}")
    private String name;

    private TrainingEngine trainingEngine;

    @Autowired
    public void setTrainingEngine(ApplicationContext context) {
        trainingEngine = (TrainingEngine) context.getBean(name);
    }

    @Scheduled(fixedDelayString="${discovery.scheduler.cron.training}", initialDelay = 0)
    public void generateModel() {
        log.info("start model generation");
        long start = System.currentTimeMillis();
        // generate model
        TrainingData model = trainingEngine.generateModel();
        log.info("model generated with engine {} in {} ms", name, System.currentTimeMillis() - start);
        // push model to service discovery
        trainingService.pushModelToDiscovery(model);

    }
}
