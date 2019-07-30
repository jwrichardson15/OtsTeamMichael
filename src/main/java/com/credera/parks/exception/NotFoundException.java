package com.credera.parks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    public NotFoundException() {super();}

    public NotFoundException(String message) {super(message);}

    public static NotFoundException ticketNotFound() {return new NotFoundException("Badge Not Found");}
    public static NotFoundException categoryNotFound() {return new NotFoundException("Category Not Found");}
    public static NotFoundException employeeNotFound() {return new NotFoundException("Employee Not Found");}
    public static NotFoundException parkNotFound() {return new NotFoundException("Park Not Found");}
}
