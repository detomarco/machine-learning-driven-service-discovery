package it.univaq.disim.discovery.servicediscovery.registry.repository.impl;

import it.univaq.disim.discovery.servicediscovery.registry.model.ServiceInstance;
import it.univaq.disim.discovery.servicediscovery.registry.repository.RegistryRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InMemoryRepository implements RegistryRepository {

    @Override
    public List<ServiceInstance> find(String serviceType) {
        return ServiceRegistryMap.getInstance().get(serviceType);
    }

    @Override
    public List<ServiceInstance> findAll() {
        return ServiceRegistryMap.getInstance().getServiceRegistryMap().values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(ServiceInstance serviceInstance) {
        this.find(serviceInstance.getName()).removeIf(serviceInstance::equals);
    }

    @Override
    public void register(ServiceInstance serviceInstance) {
        List<ServiceInstance> serviceInstances = this.find(serviceInstance.getName());
        if (CollectionUtils.isEmpty(serviceInstances)) {
            serviceInstances = new ArrayList<>();
        } else {
            serviceInstances = serviceInstances.stream()
                    .filter(s1 -> !s1.equals(serviceInstance))
                    .collect(Collectors.toList());
        }
        serviceInstances.add(serviceInstance);
        ServiceRegistryMap.getInstance().put(serviceInstance.getName(), serviceInstances);
    }

    private static class ServiceRegistryMap {

        @Getter
        private Map<String, List<ServiceInstance>> serviceRegistryMap ;
        private static ServiceRegistryMap instance;

        private ServiceRegistryMap(Map<String, List<ServiceInstance>> serviceRegistryMap) {
            this.serviceRegistryMap = serviceRegistryMap;
        }
        public static ServiceRegistryMap getInstance() {
            if (instance == null) {
                instance = new ServiceRegistryMap(new HashMap<>());
            }
            return instance;
        }


        public List<ServiceInstance> get(String serviceType) {
            return serviceRegistryMap.get(serviceType);
        }

        public void put(String name, List<ServiceInstance> serviceType) {
            serviceRegistryMap.put(name, serviceType);
        }
    }

}
