package com.example.shipmentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus( HttpStatus.BAD_REQUEST )
public class BadRequestException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new bad request exception.
     *
     * @param exception the exception
     */
    public BadRequestException( String exception ) {
        super( exception );
    }

}