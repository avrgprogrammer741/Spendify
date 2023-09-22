package com.Spendify.Spendify.User;

public record UserDTO(Long id,
                      String name,
                      String image,
                      String fullName,
                      Boolean isActive
                      ) {}
