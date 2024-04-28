package com.tzombie.store.domain.dto;

import lombok.Getter;

@Getter
public class AuthenticationRequestDTO {

    String username;

    String password;
}