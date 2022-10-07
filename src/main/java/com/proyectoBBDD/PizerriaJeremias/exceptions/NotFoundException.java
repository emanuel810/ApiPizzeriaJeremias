package com.proyectoBBDD.PizerriaJeremias.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super();
    }

    public NotFoundException(String message, Throwable cause,boolean enableSuppression, boolean writableStackTrace){
        super(message,cause,enableSuppression,writableStackTrace);
    }

    public NotFoundException(String message){
        super(message);
    }
    public NotFoundException(Throwable cause){
        super(cause);
    }

}

