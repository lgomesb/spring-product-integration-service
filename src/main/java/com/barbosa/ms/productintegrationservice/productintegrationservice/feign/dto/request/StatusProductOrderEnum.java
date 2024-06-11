package com.barbosa.ms.productintegrationservice.productintegrationservice.feign.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum StatusProductOrderEnum {
    DRAFT,
    ACCEPTED,
    REJECT,
    COMPLETED;

    public static StatusProductOrderEnum getByName(String name) {
        for(StatusProductOrderEnum value : values()) {
            if(value.name().equalsIgnoreCase(name))
                return value;
        }
        return null;
    }
}