package ru.geek.market.core.exceptions;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ValidationException extends RuntimeException{
    private List<String> fieldValidationErrors;

    public ValidationException(List<String> fieldValidationErrors){
        super(fieldValidationErrors.stream().collect(Collectors.joining(", ")));
        this.fieldValidationErrors = fieldValidationErrors;
    }
}
