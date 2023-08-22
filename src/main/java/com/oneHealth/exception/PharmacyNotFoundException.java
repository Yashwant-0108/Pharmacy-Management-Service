package com.oneHealth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The TestNotFoundException is a custom exception class.
 * It is used to represent the scenario when a test with a specific ID is not found.
 * The exception is annotated with @ResponseStatus to indicate that it results in an HTTP 404 (NOT_FOUND) status.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PharmacyNotFoundException extends Exception {

    /**
     * Generated serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor for TestNotFoundException.
     *
     * @param message The error message for the exception.
     */
    public PharmacyNotFoundException(String message) {
        super(message);
    }
}
