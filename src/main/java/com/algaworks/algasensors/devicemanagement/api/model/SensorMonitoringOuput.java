package com.algaworks.algasensors.devicemanagement.api.model;

import com.algaworks.algasensors.devicemanagement.api.config.jackson.StringToTSIDDeserializer;
import com.algaworks.algasensors.devicemanagement.api.config.jackson.TSIDToStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class SensorMonitoringOuput {

    @JsonSerialize(using = TSIDToStringSerializer.class)
    @JsonDeserialize(using = StringToTSIDDeserializer.class)
    private TSID id;
    private Double lastTemperature;
    private OffsetDateTime updatedAt;
    private Boolean enabled;


}
