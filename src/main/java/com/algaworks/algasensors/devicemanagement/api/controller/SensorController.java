package com.algaworks.algasensors.devicemanagement.api.controller;

import com.algaworks.algasensors.devicemanagement.api.model.SensorInput;
import com.algaworks.algasensors.devicemanagement.api.model.SensorOutput;
import com.algaworks.algasensors.devicemanagement.domain.model.Sensor;
import com.algaworks.algasensors.devicemanagement.domain.repository.SensorRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorRepository sensorRepository;


    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SensorOutput create(@RequestBody SensorInput input){
        Sensor sensor = Sensor.builder()
                .id(TSID.fast())
                .name(input.getIp())
                .ip(input.getIp())
                .location(input.getLocation())
                .protocol(input.getProtocol())
                .model(input.getModel())
                .enabled(false)
                .build();

         sensor = sensorRepository.saveAndFlush(sensor);
        return SensorOutput.builder()
                .id(TSID.fast())
                .name(input.getIp())
                .ip(input.getIp())
                .location(input.getLocation())
                .protocol(input.getProtocol())
                .model(input.getModel())
                .enabled(sensor.getEnabled())
                .build();
    }


}
