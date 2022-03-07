package com.viking.vikingtask01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VikingTask01Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(VikingTask01Application.class, args);
    }
}
