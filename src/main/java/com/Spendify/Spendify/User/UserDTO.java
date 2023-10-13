package com.Spendify.Spendify.User;

public record UserDTO(Long id,
                      String name,
                      String surname,
                      String fullName,
                      String image,
                      Boolean isActive
                      ) {}
