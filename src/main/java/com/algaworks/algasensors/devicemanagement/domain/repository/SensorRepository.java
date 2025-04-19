package com.algaworks.algasensors.devicemanagement.domain.repository;

import com.algaworks.algasensors.devicemanagement.domain.model.Sensor;
import io.hypersistence.tsid.TSID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, TSID> {
}
