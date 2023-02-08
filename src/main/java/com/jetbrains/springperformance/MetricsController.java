package com.jetbrains.springperformance;

import org.eclipse.jetty.perf.histogram.server.LatencyRecordingChannelListener;
import org.eclipse.jetty.perf.monitoring.AsyncProfilerCpuMonitor;
import org.eclipse.jetty.perf.monitoring.JHiccupMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.Locale;

@RestController
public class MetricsController {

    String osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);

    @Autowired
    private LatencyRecordingChannelListener latencyRecordingChannelListener;

    private JHiccupMonitor hiccupMonitor;


    private Logger logger = LoggerFactory.getLogger(MetricsController.class);

    private AsyncProfilerCpuMonitor cpuMon;

    @PostMapping("/start")
    public String start() {
        latencyRecordingChannelListener.startRecording();

        try {
            hiccupMonitor = new JHiccupMonitor();
        } catch (FileNotFoundException e) {
            logger.error("Problem instantiating HiccupMonitor", e);
        }

        try {
            if (osName.contains("linux")) {
                cpuMon = new AsyncProfilerCpuMonitor();
            }
        } catch (Exception e) {
            logger.error("Problem instantiating AsyncProfilerCpuMonitor", e);
        }
        return "OK";
    }

    @PostMapping("/stop")
    public String stop() {
        latencyRecordingChannelListener.stopRecording();
        try {
            if (hiccupMonitor != null) {
                hiccupMonitor.close();
                hiccupMonitor = null;
            }

            if (cpuMon != null) {
                cpuMon.close();
                cpuMon = null;
            }

        } catch (Exception e) {
            logger.error("Problem shutting down metrics", e);
        }
        return "OK";
    }
}
