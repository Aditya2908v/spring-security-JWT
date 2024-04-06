package org.example.springbootjwt.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
@SecurityRequirement(name ="bearerAuth")
public class AdminController {
    @GetMapping
    @PreAuthorize("hasAuthority('admin:read')")
        public String getMessage(){
        return "GET:: admin Controller";
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:update')")
    @Hidden
    public String putMessage(){
        return "PUT:: admin Controller";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin:create')")
    @Hidden
    public String postMessage(){
        return "POST:: admin Controller";
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:delete')")
    @Hidden
    public String deleteMessage(){
        return "DELETE:: admin Controller";
    }
}
