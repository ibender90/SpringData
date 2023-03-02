package ru.geek.market.core.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.geek.market.api.DTO.AppError;
import ru.geek.market.api.DTO.ResourceNotFoundException;

@ControllerAdvice //класс будет применён ко всем контроллерам
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> catchResourceNotFoundException(ResourceNotFoundException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<FieldsValidationErrorMessageCollector> catchValidationException(ValidationException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new FieldsValidationErrorMessageCollector(e.getFieldValidationErrors()), HttpStatus.BAD_REQUEST);
    }
}