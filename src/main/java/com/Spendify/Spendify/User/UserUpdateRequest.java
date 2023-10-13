package com.Spendify.Spendify.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UserUpdateRequest(
        @NotNull(message = "name can't be null")
        String name,
        @NotNull(message = "surname can't be null")
        String surname,
        @NotNull(message = "password can't be null")
        String password,
        @Email(message = "email can't be null")
        String email,
        @NotNull(message = "image can't be null")
        String image
) {
}