package com.algaworks.algasensors.devicemanagement.api.controller;

import com.algaworks.algasensors.devicemanagement.api.model.SensorInput;
import com.algaworks.algasensors.devicemanagement.api.model.SensorOutput;
import com.algaworks.algasensors.devicemanagement.domain.model.Sensor;
import com.algaworks.algasensors.devicemanagement.domain.model.SensorId;
import com.algaworks.algasensors.devicemanagement.domain.repository.SensorRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorRepository sensorRepository;

    @GetMapping
    public Page<SensorOutput> search( @PageableDefault Pageable pagable){
        Page<Sensor> sensors = sensorRepository.findAll(pagable);
        return sensors.map(this::convertToModel);
    }

    @PutMapping("/{sensorId}")
    public SensorOutput updateSensor(
            @PathVariable TSID sensorId,
            @RequestBody SensorInput input
    ) {
        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        sensor.setName(input.getName());
        sensor.setLocation(input.getLocation());
        sensor.setIp(input.getIp());
        sensor.setModel(input.getModel());
        sensor.setProtocol(input.getProtocol());

        Sensor updated = sensorRepository.save(sensor);
        return convertToModel(updated);
    }


    @DeleteMapping("/{sensorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable TSID sensorId) {
        Sensor sensor = sensorRepository.findById(new SensorId(sensorId).getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        sensorRepository.delete(sensor);
    }

    @PutMapping("/{sensorId}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(@PathVariable TSID sensorId) {
        Sensor sensor = sensorRepository.findById(new SensorId(sensorId).getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        sensor.setEnabled(true);
        sensorRepository.save(sensor);
    }


    @DeleteMapping("/{sensorId}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable TSID sensorId) {
        Sensor sensor = sensorRepository.findById(new SensorId(sensorId).getValue())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        sensor.setEnabled(false);
        sensorRepository.save(sensor);
    }



    @GetMapping("{sensorId}")
    public SensorOutput getOne(@PathVariable TSID sensorId){
        Sensor sensor = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return convertToModel(sensor);
    }

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
        return convertToModel( sensor);
    }

    private  SensorOutput convertToModel( Sensor sensor) {
        return SensorOutput.builder()
                .id(sensor.getId())
                .name(sensor.getIp())
                .ip(sensor.getIp())
                .location(sensor.getLocation())
                .protocol(sensor.getProtocol())
                .model(sensor.getModel())
                .enabled(sensor.getEnabled())
                .build();
    }


}
