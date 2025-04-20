package com.algaworks.algasensors.devicemanagement.api.client.impl;

import com.algaworks.algasensors.devicemanagement.api.client.SensorMonitoringClient;
import io.hypersistence.tsid.TSID;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SensorMonitoringClientImpl implements SensorMonitoringClient {

    private RestClient restClient;

    public void SensorMonitoringClientImpl(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://localhost:8082")
                .build();
    }


    @Override
    public void enableMonitoring(TSID sensorId) {
        restClient.post()
                .uri("/api/sensors/{sensorId}/monitoring/enable", sensorId)
                .retrieve()
                .toBodilessEntity();

    }

    @Override
    public void disableMonitoring(TSID sensorId) {

        restClient.delete()
                .uri("/api/sensors/{sensorId}/monitoring/enable", sensorId)
                .retrieve()
                .toBodilessEntity();

    }
}
