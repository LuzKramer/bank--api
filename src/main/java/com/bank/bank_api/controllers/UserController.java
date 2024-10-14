package com.bank.bank_api.controllers;

import com.bank.bank_api.transaction.Transaction;
import com.bank.bank_api.transaction.TransactionRequestDTO;
import com.bank.bank_api.user.User;
import com.bank.bank_api.user.UserRepository;
import com.bank.bank_api.user.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok("success");
    }

    @Autowired
    private UserRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            // Convert User to UserResponseDTO (Assuming you have a method to do so)
            UserResponseDTO userDTO = new UserResponseDTO(user.get());
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}

