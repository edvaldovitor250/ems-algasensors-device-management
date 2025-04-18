package com.algaworks.algasensors.devicemanagement.domain.repository;

import com.algaworks.algasensors.devicemanagement.domain.model.Sensor;
import com.algaworks.algasensors.devicemanagement.domain.model.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, SensorId> {
}
