package com.lucas.project.projectspringboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private Object fielValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue){
        super(String.format("%s no fue encontrado: %s = %s", resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fielValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName){
        super(String.format("No hay registros de %s en el sistema", resourceName));
        this.resourceName = resourceName;
        
    }
}
