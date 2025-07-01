package com.contact.contactmanager.Controller;

import com.contact.contactmanager.Dtos.ApiResponse;
import com.contact.contactmanager.Dtos.AuthRequest;
import com.contact.contactmanager.Dtos.AuthResponse;
import com.contact.contactmanager.Repository.AppUserRepository;
import com.contact.contactmanager.Repository.UserDataRepository;
import com.contact.contactmanager.Service.AppUserDetailsService;
import com.contact.contactmanager.Util.JwtUtil;
import com.contact.contactmanager.model.AppUser;


import com.contact.contactmanager.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
                          UserDetailsService userDetailsService, AppUserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


//    @GetMapping("/userinfo")
//    public ResponseEntity<AppUser> getUserInfo(@PathVariable Username ){
//        AppUser
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.username());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody AuthRequest authRequest) {
        if (userRepository.existsById(authRequest.username())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("Username already exists"));
        }

        AppUser user = new AppUser(authRequest.username(),
                passwordEncoder.encode(authRequest.password()),authRequest.email(),authRequest.phoneNumber(),
                "ROLE_USER");
        userRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse("User registered successfully"));
    }
}