package com.Spendify.Spendify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)

public class QuantityBiggerThanAmountLeftException extends RuntimeException{
    public QuantityBiggerThanAmountLeftException(String message) {
        super(message);
    }

}
