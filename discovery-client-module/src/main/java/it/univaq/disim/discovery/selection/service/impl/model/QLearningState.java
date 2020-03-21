package it.univaq.disim.discovery.selection.service.impl.model;

import com.google.common.collect.Table;
import lombok.Data;
import org.javatuples.Pair;

@Data
public class QLearningState {

    private Pair<String, String> currentState;
    private Table<Pair<Pair<String, String>, String>, String, Double> qTable;
    private Table<String, String, Boolean> environment;

}
