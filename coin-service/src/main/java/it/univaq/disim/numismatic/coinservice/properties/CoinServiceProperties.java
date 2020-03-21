package it.univaq.disim.numismatic.coinservice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@Component
@Valid
@ConfigurationProperties(prefix = "custom")
public class CoinServiceProperties {

    @NotNull
    private String context;

    @NotNull
    @NotEmpty
    @Size(min = 24, max = 24)
    private Map<Integer, Double> delay;

}
