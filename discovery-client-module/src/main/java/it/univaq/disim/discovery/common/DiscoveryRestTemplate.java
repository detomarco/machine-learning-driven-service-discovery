package it.univaq.disim.discovery.common;

import it.univaq.disim.discovery.common.domain.RankedServicesInstance;
import it.univaq.disim.discovery.common.domain.ServiceInstance;
import it.univaq.disim.discovery.common.property.DiscoveryProperties;
import it.univaq.disim.discovery.monitoring.model.MonitoringData;
import it.univaq.disim.discovery.monitoring.model.RequestStatus;
import it.univaq.disim.discovery.monitoring.repository.Repository;
import it.univaq.disim.discovery.selection.service.SelectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DiscoveryRestTemplate {

    @Autowired
    private DiscoveryProperties discoveryProperties;

    @Autowired
    private Repository repository;

    private SelectionService selectionService;

    @Autowired
    @DependsOn("discoveryProperties")
    public void setMlEngineService(ApplicationContext context) {
        selectionService = (SelectionService) context.getBean(discoveryProperties.getStrategy().getSelection());
    }

    public ServiceInstance retrieveInstance(String serviceType) {
        List<String> urls = discoveryProperties.getAddress().getServiceDiscovery().stream()
                .map(serviceDiscoveryAddress -> String.format("%s/%s/%s", serviceDiscoveryAddress, discoveryProperties.getContext(), serviceType))
                .collect(Collectors.toList());

        RankedServicesInstance instanceServices = this.exchangeMultipleUrl(urls, HttpMethod.GET, null, null, RankedServicesInstance.class);

        ServiceInstance serviceInstance = selectionService.selectInstance(serviceType, instanceServices.getInstances());
        log.info("Selected instance {}", serviceInstance.getUrl());
        return serviceInstance;
    }

    public <T, R> R post(String serviceType, String path, @Nullable T request, Class<R> responseType) {
        return exchange(serviceType, path, HttpMethod.POST, request, null, null, responseType);
    }

    public <R> R get(String serviceType, String path, Class<R> responseType) {
        return exchange(serviceType, path, HttpMethod.GET, null, null, null, responseType);
    }

    public <T, R> R exchange(String serviceType, String path, HttpMethod method, T body, HttpHeaders httpHeaders, Map<String, String[]> parameter, Class<R> responseClass) {
        ServiceInstance serviceInstance = this.retrieveInstance(serviceType);
        Date startTime = new Date();

        try {
            R response = this.exchange(serviceInstance.getUrl() + path, method, body, httpHeaders, parameter, responseClass);
            MonitoringData monitoringData = save(serviceInstance, startTime, RequestStatus.SUCCESS);
            selectionService.postAction(monitoringData);
            return response;
        } catch (HttpClientErrorException e) {
            save(serviceInstance, startTime, RequestStatus.ERROR);
            log.error(e.getMessage(), e);
            throw e;
        }

    }

    private <T, R> R exchangeMultipleUrl(List<String> urls, HttpMethod method, T body, HttpHeaders httpHeaders, Class<R> responseType) {
        List<String> shuffledUrlList = new ArrayList<>(urls);
        Collections.shuffle(shuffledUrlList);

        Boolean success = Boolean.FALSE;
        R result = null;
        RuntimeException ex = null;
        for (String url : shuffledUrlList) {
            try {
                result = this.exchange(url, method, body, httpHeaders, null, responseType);
                success = Boolean.TRUE;
                break;
            } catch (RuntimeException e) {
                log.error("Endpoint {} not available", url);
                ex = e;
            }
        }

        if (success.equals(Boolean.FALSE) && ex != null) {
            throw ex;
        }

        return result;
    }

    private <T, R> R exchange(String url, HttpMethod method, T body, HttpHeaders httpHeaders, Map<String, String[]> parameter, Class<R> responseType) {
        return new RestTemplate().exchange(
                url,
                method,
                new HttpEntity<>(body, httpHeaders),
                responseType,
                parameter != null ? parameter : new HashMap<>()
        ).getBody();
    }

    private MonitoringData save(ServiceInstance serviceInstance, Date startDate, RequestStatus status) {
        MonitoringData monitoringData = new MonitoringData();
        monitoringData.setServiceType(serviceInstance.getName());
        monitoringData.setServiceInstance(serviceInstance.getUrl());
        monitoringData.setTimestamp(startDate);
        monitoringData.setResponseTime(new Date().getTime() - startDate.getTime());
        monitoringData.setStatus(status);
        monitoringData.setServiceContext(serviceInstance.getContext());
        monitoringData.setClientContext(discoveryProperties.getContext());
        monitoringData.setMeanResponseTime(repository.getMeanResponseTime());
        repository.monitorData(monitoringData, startDate);
        return monitoringData;
    }

}
