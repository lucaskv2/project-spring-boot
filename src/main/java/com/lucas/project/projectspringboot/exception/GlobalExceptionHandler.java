package com.lucas.project.projectspringboot.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.lucas.project.projectspringboot.model.payload.ApiResponse;

@RestControllerAdvice // Indica q esta clase va a detectar todos los errores q se van a producir en el controlador.
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class) //Esta anotacion permite obtener los resultados en tiempo de ejecución de estos errores. 
    public ResponseEntity<Object> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                    WebRequest webRequest){
                                                                        Map<String,String> mapErrors = new HashMap<>();
                                                                        exception.getBindingResult().getAllErrors().forEach((error) ->{
                                                                                String clave = ((FieldError)error).getField();
                                                                                String valor = error.getDefaultMessage();
                                                                                mapErrors.put(clave, valor);
                                                                            }
                                                                        );
                                                                        ApiResponse apiResponse = new ApiResponse(mapErrors.toString(), webRequest.getDescription(false));
                                                                        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
                                                                    }

    @ExceptionHandler(ResourceNotFoundException.class) //Esta anotacion permite obtener los resultados en tiempo de ejecución de estos errores. 
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException exception,
                                                                    WebRequest webRequest){
                                                                        ApiResponse apiResponse = new ApiResponse(exception.getMessage(), webRequest.getDescription(false));
                                                                        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
                                                                    }
}
