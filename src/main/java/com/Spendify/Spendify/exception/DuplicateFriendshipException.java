package com.Spendify.Spendify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(code= HttpStatus.CONFLICT)
public class DuplicateFriendshipException extends RuntimeException{
    public DuplicateFriendshipException(String message)
    {
        super(message);
    }
}