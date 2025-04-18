package com.algaworks.algasensors.devicemanagement.domain.model;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Sensor {
    @Id
    @Column(name = "id", columnDefinition = "BIGINT")
    private TSID id;

    private String name;
    private String ip;
    private String location;
    private String protocol;
    private String model;
    private Boolean enabled;

}
