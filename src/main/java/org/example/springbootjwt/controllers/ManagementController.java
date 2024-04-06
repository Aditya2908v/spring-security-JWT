package org.example.springbootjwt.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management")
@Tag(name = "Management")
public class ManagementController {

    @Operation(
            description = "Get endpoint for manager",
            summary = "This is a summary for management get endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }
    )
    @GetMapping
    public String getMessage(){
        return "GET:: management controller";
    }

    @PostMapping
    public String postMessage(){
        return "POST:: management controller";
    }

    @PutMapping
    public String putMessage(){
        return "PUT:: management controller";
    }

    @DeleteMapping
    public String deleteMessage(){
        return "DELETE:: management controller";
    }
}
