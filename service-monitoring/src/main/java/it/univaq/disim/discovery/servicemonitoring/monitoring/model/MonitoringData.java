package it.univaq.disim.discovery.servicemonitoring.monitoring.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "monitoring_data")
public class MonitoringData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @Column(nullable = false)
    private String clientContext;
    @NotNull @Column(nullable = false)
    private String serviceType;
    @NotNull @Column(nullable = false)
    private String serviceInstance;
    @NotNull @Column(nullable = false)
    private String serviceContext;
    @NotNull @Column(nullable = false)
    private Date timestamp;
    @NotNull @Column(nullable = false)
    private Long responseTime;
    @NotNull @Column(nullable = false)
    private Double meanResponseTime;
    @NotNull @Column(nullable = false)
    private RequestStatus status;

    @Column(nullable = false)
    private String predictionStrategy;
    @Column(nullable = false)
    private String selectionStrategy;

}
