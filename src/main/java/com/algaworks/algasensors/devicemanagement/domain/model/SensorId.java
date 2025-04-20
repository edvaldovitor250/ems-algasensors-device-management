package com.algaworks.algasensors.devicemanagement.domain.model;


import io.hypersistence.tsid.TSID;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class SensorId implements Serializable {

    private TSID value;

    public SensorId(TSID value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public SensorId(Long value) {
        Objects.requireNonNull(value);
        this.value = TSID.from(value);
    }

    public SensorId(String value) {
        Objects.requireNonNull(value);
        this.value = TSID.from(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
