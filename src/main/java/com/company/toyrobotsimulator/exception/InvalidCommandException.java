package com.company.toyrobotsimulator.exception;

/**
 * An exception for all Invalid commands
 */
public class InvalidCommandException extends RuntimeException {
    private static final long serialVersionUID = -7859797276405021743L;

    public InvalidCommandException(String message){
        super(message);
    }
}
