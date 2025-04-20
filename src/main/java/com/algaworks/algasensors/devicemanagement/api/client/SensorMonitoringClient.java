package com.algaworks.algasensors.devicemanagement.api.client;

import com.algaworks.algasensors.devicemanagement.api.model.SensorMonitoringOuput;
import io.hypersistence.tsid.TSID;

public interface SensorMonitoringClient {
    void enableMonitoring(TSID sensorId);
    void disableMonitoring(TSID sensorId);
    SensorMonitoringOuput getDetail(TSID sensorId);
}