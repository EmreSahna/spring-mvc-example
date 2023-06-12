package com.emresahna.springmvcexample.service;

import com.emresahna.springmvcexample.dto.RegistrationDto;
import com.emresahna.springmvcexample.model.User;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    boolean isUsernameTaken(String username);
    boolean isEmailTaken(String email);
    User findByUsername(String username);
}
