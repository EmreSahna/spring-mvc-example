package com.emresahna.springmvcexample.service.impl;

import com.emresahna.springmvcexample.dto.RegistrationDto;
import com.emresahna.springmvcexample.model.User;
import com.emresahna.springmvcexample.repository.UserRepository;
import com.emresahna.springmvcexample.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.emresahna.springmvcexample.mapper.UserMapper.registrationDtoToUser;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        registrationDto.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        User user = registrationDtoToUser(registrationDto);
        userRepository.save(user);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }
}
