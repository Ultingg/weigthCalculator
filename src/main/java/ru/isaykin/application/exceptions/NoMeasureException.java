package ru.isaykin.application.exceptions;

public class NoMeasureException extends NullPointerException {

   public NoMeasureException(String errorMessage) {
        super(errorMessage);
    }
}
