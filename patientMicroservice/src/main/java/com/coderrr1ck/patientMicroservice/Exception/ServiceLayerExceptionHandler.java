package com.coderrr1ck.patientMicroservice.Exception;

import java.util.Map;

public class ServiceLayerExceptionHandler extends RuntimeException {
    private final Map<String,String> errors;
    public ServiceLayerExceptionHandler(Map errors){
        super("Service layer exception occured.");
        this.errors = errors;
    }
    public Map getErrors(){
        return errors;
    }
}
