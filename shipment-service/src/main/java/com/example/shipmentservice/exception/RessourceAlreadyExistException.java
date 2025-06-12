package com.example.shipmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


// TODO: Auto-generated Javadoc
/**
 * The Class RessourceAlreadyExistException.
 */
@ResponseStatus( HttpStatus.CONFLICT )
public class RessourceAlreadyExistException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new ressource already exist exception.
     *
     * @param message the message
     */
    public RessourceAlreadyExistException( final String message ) {
        super( message );
    }

}