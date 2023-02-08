package com.jetbrains.springperformance;

import org.eclipse.jetty.perf.histogram.server.LatencyRecordingChannelListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileNotFoundException;

@SpringBootApplication
public class SpringPerformanceApplication {

    @Bean
    public LatencyRecordingChannelListener latencyRecordingChannelListener() throws FileNotFoundException {
        return new LatencyRecordingChannelListener();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringPerformanceApplication.class, args);
    }

}
