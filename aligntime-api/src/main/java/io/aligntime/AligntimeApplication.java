package io.aligntime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "io.aligntime")
public class AligntimeApplication {
    public static void main(String[] args) {
        SpringApplication.run(AligntimeApplication.class, args);
    }
}
