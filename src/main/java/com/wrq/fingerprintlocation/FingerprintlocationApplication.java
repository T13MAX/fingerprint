package com.wrq.fingerprintlocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FingerprintlocationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FingerprintlocationApplication.class, args);
    }

}
