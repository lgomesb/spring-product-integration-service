package com.barbosa.ms.productintegrationservice.productintegrationservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public class ApiErrors {

    private List<String> errors;

    public ApiErrors(String error) {
        this.errors = Arrays.asList(error);
    }
    
}
