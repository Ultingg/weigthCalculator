package ru.isaykin.application.exceptions;

public class NoTruckException extends NullPointerException {

  public  NoTruckException() {
        super();
        System.out.println("There is no such truck in database");
    }
}
