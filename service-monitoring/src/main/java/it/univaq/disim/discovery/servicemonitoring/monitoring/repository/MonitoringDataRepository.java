package it.univaq.disim.discovery.servicemonitoring.monitoring.repository;

import it.univaq.disim.discovery.servicemonitoring.monitoring.model.MonitoringData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitoringDataRepository extends PagingAndSortingRepository<MonitoringData, Long> {

    List<MonitoringData> findByServiceInstance(String serviceInstance, Pageable pageable);

    List<MonitoringData> findAll();
}

