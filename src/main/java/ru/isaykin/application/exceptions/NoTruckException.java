package ru.isaykin.application.exceptions;

public class NoTruckException extends NullPointerException {

    public NoTruckException(String errorMessage) {
        super(errorMessage);
    }
}
