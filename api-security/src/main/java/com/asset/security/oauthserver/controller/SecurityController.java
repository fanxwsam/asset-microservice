package com.asset.security.oauthserver.controller;

import com.asset.security.oauthserver.models.ClientRegistration;
import com.asset.security.oauthserver.models.PasswordChange;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Security", description = "Security management APIs")
@RestController
@RequestMapping("/api/v1/security")
public class SecurityController {

    @Operation(
            summary = "reset the password",
            description = "Provide old user name, old password and new password",
            tags = { "Security", "POST" })
    @PostMapping(value="/password-reset/{email_address}")
    public ResponseEntity<?> resetPassword(@PathVariable("email_address") String emailAddressIn){
        System.out.println("Reset Password Called.");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password-change")
    @Operation(summary="Allows a user to change their password.")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChange passwordChangeIn){
        System.out.println("Reset Password Called.");
        return ResponseEntity.ok().build();
    }

    @Operation(summary="Create new OAuth2 Client")
    @PostMapping("/client-registration")
    public ResponseEntity<?> createClient(@RequestBody ClientRegistration clientRegistrationIn) {
        System.out.println("client-registration Called.");
        return ResponseEntity.ok().build();
    }
}
