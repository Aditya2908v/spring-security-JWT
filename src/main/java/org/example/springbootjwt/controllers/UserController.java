package org.example.springbootjwt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @GetMapping
    public String getMessage(){
        return "GET:: user Controller";
    }
    @PostMapping
    public String postMessage(){
        return "POST:: user Controller";
    }

    @DeleteMapping
    public String deleteMessage(){
        return "DELETE:: user Controller";
    }

    @PutMapping
    public String putMessage(){
        return "PUT:: user Controller";
    }
}
