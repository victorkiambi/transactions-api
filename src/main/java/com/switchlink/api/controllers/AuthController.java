package com.switchlink.api.controllers;

import com.switchlink.api.models.Customer;
import com.switchlink.api.payload.response.JwtResponse;
import com.switchlink.api.payload.LoginRequest;
import com.switchlink.api.payload.response.MessageResponse;
import com.switchlink.api.repositories.CustomerRepository;
import com.switchlink.api.security.jwt.JwtUtils;
import com.switchlink.api.services.CustomerDetailsImpl;
import com.switchlink.api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, CustomerRepository customerRepository, CustomerService customerService, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public String authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        return "we are here"+ authentication;
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        CustomerDetailsImpl customerDetails = (CustomerDetailsImpl) authentication.getPrincipal();
//
//        return ResponseEntity.ok(new JwtResponse(jwt,
//                customerDetails.getCustomerId(),
//                customerDetails.getUsername(),
//                customerDetails.getCustomerEmail(),
//                customerDetails.getCustomerPhone()
//               ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Customer signUpRequest) {
        if (customerRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new   MessageResponse("Error: Username is already taken!"));
        }

        if (customerRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

//        ResponseEntity<Object> customer = customerService.save(signUpRequest);

         //Create new user's account
        Customer customer = new Customer();
        customer.setUsername(signUpRequest.getUsername());
        customer.setEmail(signUpRequest.getEmail());
        customer.setPhone(signUpRequest.getPhone());
        customer.setPassword(encoder.encode(signUpRequest.getPassword()));
        customerRepository.save(customer);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!" + customer));
    }
}
