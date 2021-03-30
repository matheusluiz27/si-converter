package com.si.converter.exceptions;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(value = AccessLevel.NONE)
public class InvalidUnitsException extends RuntimeException {

    public InvalidUnitsException() {
        super("Invalid units");
    }

    public InvalidUnitsException(String message) {
        super(message);
    }
}
