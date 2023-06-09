package com.emresahna.springmvcexample.controller;

import com.emresahna.springmvcexample.dto.RegistrationDto;
import com.emresahna.springmvcexample.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("user",registrationDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String registerUser(@Valid @ModelAttribute RegistrationDto registrationDto,
                               BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("user",registrationDto);
            return "register";
        }

        if(userService.isEmailTaken(registrationDto.getEmail())){
            model.addAttribute("user",registrationDto);
            return "register?error";
        }

        if(userService.isUsernameTaken(registrationDto.getUsername())){
            model.addAttribute("user",registrationDto);
            return "register?error";
        }

        userService.saveUser(registrationDto);
        return "redirect:/clubs?success";
    }
}
