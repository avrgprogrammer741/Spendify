package com.Spendify.Spendify.User;

public record UserUpdateRequest(
        String name,
        String email,
        Integer age
) {
}