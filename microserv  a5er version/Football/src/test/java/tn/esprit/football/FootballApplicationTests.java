package tn.esprit.football;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootTest
@EnableAspectJAutoProxy
@EnableScheduling

@EnableDiscoveryClient
class FootballApplicationTests {

    @Test
    void contextLoads() {
    }

}
