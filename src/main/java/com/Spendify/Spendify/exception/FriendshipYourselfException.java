package com.Spendify.Spendify.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT)
public class FriendshipYourselfException extends RuntimeException{
    public FriendshipYourselfException(String message)
    {
        super(message);
    }
}