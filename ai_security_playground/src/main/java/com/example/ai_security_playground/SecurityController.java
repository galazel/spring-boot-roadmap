package com.example.ai_security_playground;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sample/")
public class SecurityController {
    @GetMapping("public")
    public String publicMethod() {
        return "public";
    }
    @GetMapping("private")
    public String privateMethod() {
        return "private";
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("whoami")
    public String whoAmI() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return String.format(
                """
                Principal: %s \n
                Details: %s \n
                Authenticated: %s
                """
                , auth.getName(),auth.getDetails().toString(),auth.isAuthenticated());
    }

}
