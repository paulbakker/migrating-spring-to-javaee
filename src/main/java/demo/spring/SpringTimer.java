package demo.spring;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringTimer {
    @Scheduled(fixedDelay = 50000)
    public void ping() {
        System.out.println("pong...");
    }
}
