package com.example.shipmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus( HttpStatus.NOT_FOUND )
public class RessourceNotFoundException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new ressource not found exception.
     *
     * @param message the message
     */
    public RessourceNotFoundException( final String message ) {
        super( message );
    }

}