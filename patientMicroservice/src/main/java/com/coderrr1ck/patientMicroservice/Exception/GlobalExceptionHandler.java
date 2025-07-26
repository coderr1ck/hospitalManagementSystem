package com.coderrr1ck.patientMicroservice.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> hadleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors  = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                err->errors.put(err.getField(),err.getDefaultMessage()
                )
        );
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(ServiceLayerExceptionHandler.class)
    public ResponseEntity<Map<String,String>> hadleValidationException(ServiceLayerExceptionHandler ex){
        log.warn("Service layer exception occured : "+ex.getErrors());
        return ResponseEntity.badRequest().body(ex.getErrors());
    }


}
