package it.univaq.disim.discovery.common.filter;

import it.univaq.disim.discovery.common.property.DiscoveryProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.DataInput;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
public class DelayFilter implements Filter {

    @Autowired
    private DiscoveryProperties discoveryProperties;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        long seconds = sleep();
        chain.doFilter(request, response);
        log.info("Response time {} with a sleep of {} seconds", System.currentTimeMillis() - start, seconds);
    }

    private double random(double mean) {
        // Return an exponential random variate with mean "mean".
        return -mean * Math.log(Math.random());
    }

    public long sleep() {
        Map<Integer, Double> delay = discoveryProperties.getDelay();
        if (delay != null) {
            try {
                long sleep = (long) (random(delay.get(LocalDateTime.now().getHour())) * 1000);
                Thread.sleep((long) (sleep * 1));
                return sleep;
            } catch (InterruptedException e) {
                log.error(e.getLocalizedMessage(), e);
            }
        }

        return 0;

    }
}
