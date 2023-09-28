package com.Spendify.Spendify.exception;

import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

public record ApiError(
        String path,
        String message,
        int statusCode,
        LocalDateTime localDateTime
) {
}