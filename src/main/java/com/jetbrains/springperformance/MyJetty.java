package com.jetbrains.springperformance;

import org.eclipse.jetty.perf.histogram.server.LatencyRecordingChannelListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.jetty.ConfigurableJettyWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class MyJetty implements WebServerFactoryCustomizer<ConfigurableJettyWebServerFactory> {

    @Autowired
    private LatencyRecordingChannelListener latencyRecordingChannelListener;

    @Override
    public void customize(ConfigurableJettyWebServerFactory server) {
        server.addServerCustomizers(jetty -> {
                jetty.addBean(latencyRecordingChannelListener);
        });
    }
}