package com.tzombie.store.controller;

import com.tzombie.store.domain.dto.AuthUserDTO;
import com.tzombie.store.domain.dto.AuthenticationRequestDTO;
import com.tzombie.store.jwt.JwtProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication")
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;


    @PostMapping("/token")
    @Operation(summary = "Authenticate user")
    public ResponseEntity<AuthUserDTO> token(@RequestBody AuthenticationRequestDTO dto) {
        try {
            var authenticationToken = (Authentication) new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            authenticationToken = authenticationManager.authenticate(authenticationToken);
            var username = "";
            if (authenticationToken.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
                username = ((org.springframework.security.core.userdetails.User) authenticationToken.getPrincipal()).getUsername();
            }
            var tokenInfo = jwtProvider.generateToken(username);
            return ResponseEntity.ok().body(new AuthUserDTO(username, tokenInfo));
        } catch (AuthenticationException ignored) {
            ignored.printStackTrace();
            throw new RuntimeException("Invalid username or password.");
        }
    }
}
