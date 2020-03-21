package it.univaq.disim.numismatic.userservice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Map;

@Data
@Component
@Valid
@ConfigurationProperties(prefix = "custom")
public class UserServiceProperties {

    private String context;
    private Map<Integer, Double> delay;

}
