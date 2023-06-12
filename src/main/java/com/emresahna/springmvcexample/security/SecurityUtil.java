package com.emresahna.springmvcexample.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static Authentication getSession() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
