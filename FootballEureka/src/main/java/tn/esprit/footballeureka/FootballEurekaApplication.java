package tn.esprit.footballeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FootballEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballEurekaApplication.class, args);
    }

}
