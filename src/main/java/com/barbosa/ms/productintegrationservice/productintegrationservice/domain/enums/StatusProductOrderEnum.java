package com.barbosa.ms.productintegrationservice.productintegrationservice.domain.enums;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum StatusProductOrderEnum {
    DRAFT,
    APPROVED,
    REJECTED,
    COMPLETED;

    public static StatusProductOrderEnum getByName(String name) {
        for(StatusProductOrderEnum value : values()) {
            if(value.name().equalsIgnoreCase(name))
                return value;
        }
        return null;
    }
}