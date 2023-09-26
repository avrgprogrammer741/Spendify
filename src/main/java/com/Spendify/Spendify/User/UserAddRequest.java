package com.Spendify.Spendify.User;

public record UserAddRequest(String name,
                             String surname,
                             String password,
                             String email,
                             String image,
                             Boolean isActive
                             ) {

}
