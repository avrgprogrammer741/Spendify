package com.Spendify.Spendify.exception;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ApiError(
        String path,
        String message,
        LocalDateTime localDateTime,
        List<String> additionalData
) {
}