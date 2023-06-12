package com.emresahna.springmvcexample.mapper;

import com.emresahna.springmvcexample.dto.RegistrationDto;
import com.emresahna.springmvcexample.model.Role;
import com.emresahna.springmvcexample.model.User;

import java.util.Collections;

public class UserMapper {
    public static User registrationDtoToUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        user.setRole(Collections.singleton(Role.USER));
        return user;
    }
}
