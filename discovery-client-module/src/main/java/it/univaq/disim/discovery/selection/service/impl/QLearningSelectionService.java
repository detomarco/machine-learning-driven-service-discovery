package it.univaq.disim.discovery.selection.service.impl;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import it.univaq.disim.discovery.common.domain.RankedServiceInstance;
import it.univaq.disim.discovery.common.domain.ServiceInstance;
import it.univaq.disim.discovery.common.property.DiscoveryProperties;
import it.univaq.disim.discovery.common.utils.Utils;
import it.univaq.disim.discovery.selection.service.SelectionService;
import it.univaq.disim.discovery.monitoring.model.MonitoringData;
import it.univaq.disim.discovery.selection.service.impl.model.QLearningState;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service("q-learning")
public class QLearningSelectionService implements SelectionService {

    @Autowired
    private DiscoveryProperties discoveryProperties;

    public static final float gamma = 0.1f;  // Learning rate
    public static final float epsilon = 0.08f;
    public static final float decay = 0.1f;
    public static final String[] categories;
    public static final Map<String, Double> categoriesRewards;
    private final Map<String, QLearningState> qLearningStateMap = new HashMap<>();

    static {
        categories = new String[]{"LOW", "MEDIUM", "HIGH"};
        categoriesRewards = new HashMap<>();
        categoriesRewards.put(categories[0], .2D);
        categoriesRewards.put(categories[1], 0D);
        categoriesRewards.put(categories[2], -.2D);
    }

    @Override
    public ServiceInstance selectInstance(String serviceType, List<RankedServiceInstance> serviceInstances) {

        QLearningState qLearningState = qLearningStateMap.get(serviceType);

        if (qLearningState == null) {
            qLearningState = new QLearningState();
            // init q table
            qLearningState.setQTable(initializeQTable(serviceInstances));
            qLearningState.setEnvironment(initializeEnvironment(serviceInstances));
            qLearningState.setCurrentState(Pair.with(discoveryProperties.getContext(), categories[0]));
            qLearningStateMap.put(serviceType, qLearningState);
        }

        Table<Pair<Pair<String, String>, String>, String, Double> qTable = qLearningState.getQTable();

        // Select action
        String action;
        if (Math.random() < epsilon) {
            Set<String> columns = qTable.columnKeySet();
            action = Utils.getRandomElement(columns);

        } else {
            action = maxAtRow(qLearningState.getQTable(), qLearningState.getCurrentState()).getColumnKey();
        }

        // perform action selecting instance
        return serviceInstances.stream()
                .filter(serviceInstance -> serviceInstance.getUrl().equals(action))
                .findFirst().orElse(null);
    }

    /**
     * update Q-table and current state
     *
     * @param monitoringData monitoring data
     */
    @Override
    public void postAction(MonitoringData monitoringData) {
        QLearningState qLearningState = qLearningStateMap.get(monitoringData.getServiceType());
        String category = getCategoryByResponseTime(monitoringData.getResponseTime());
        Double reward = categoriesRewards.get(category);
        Pair<String, String> currentState = qLearningState.getCurrentState();
        qLearningState.setCurrentState(Pair.with(monitoringData.getServiceContext(), category));
        Double newValue = gamma + reward * maxAtRow(qLearningState.getQTable(), qLearningState.getCurrentState()).getValue();
        getCells(qLearningState.getQTable(), currentState)
                .forEach(cell -> qLearningState.getQTable().put(cell.getRowKey(), cell.getColumnKey(), newValue));
    }

    private static Table<Pair<Pair<String, String>, String>, String, Double> initializeQTable(List<RankedServiceInstance> serviceInstances) {

        List<Pair<Pair<String, String>, String>> rows = new ArrayList<>();

        serviceInstances.stream()
                // retrieve context
                .map(ServiceInstance::getContext)
                // concat contexts with each category
                .flatMap(context -> Arrays.stream(categories)
                        .map(category -> new ArrayList<>(Arrays.asList(context, category))))
                .distinct()
                .forEach(state -> serviceInstances.stream()
                        .filter(serviceInstance -> serviceInstance.getContext().equals(state.get(0)))
                        .forEach(serviceInstance -> rows.add(Pair.with(Pair.with(state.get(0), state.get(1)), serviceInstance.getUrl())))
                );

        // create table with random value
        Table<Pair<Pair<String, String>, String>, String, Double> qTable = HashBasedTable.create();
        rows.forEach(row ->
                serviceInstances.forEach(serviceInstance ->
                        qTable.put(row, serviceInstance.getUrl(), 1D - serviceInstance.getRanking())));

        return qTable;

    }

    private static Table<String, String, Boolean> initializeEnvironment(List<RankedServiceInstance> serviceInstances) {

        // create table with random value
        Table<String, String, Boolean> qTable = HashBasedTable.create();
        serviceInstances.forEach(serviceInstance ->
                Arrays.stream(categories).forEach(category ->
                        qTable.put(serviceInstance.getContext(), category, Boolean.FALSE)));

        return qTable;
    }

    private Table.Cell<Pair<Pair<String, String>, String>, String, Double> maxAtRow(Table<Pair<Pair<String, String>, String>, String, Double> table, Pair<String, String> state) {
        return getCells(table, state).stream()
                .max(Comparator.comparing(Table.Cell::getValue))
                .orElse(null);
    }


    private List<Table.Cell<Pair<Pair<String, String>, String>, String, Double>> getCells(Table<Pair<Pair<String, String>, String>, String, Double> table, Pair<String, String> state) {
        return table.cellSet()
                .stream().filter(cell -> cell.getRowKey() != null && cell.getRowKey().getValue0().equals(state))
                .collect(Collectors.toList());
    }

    private static String getCategoryByResponseTime(Long responseTime) {
        if (responseTime < 1L) {
            return categories[0];
        } else if (responseTime < 3L) {
            return categories[1];
        } else {
            return categories[2];
        }
    }
}
