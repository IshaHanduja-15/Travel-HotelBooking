package com.nagp.microservices.flightsearch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FlightSearchException extends RuntimeException{

  /*  public FlightSearchException(String message){
        super(message);
    }*/

    private String message;

    public FlightSearchException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

