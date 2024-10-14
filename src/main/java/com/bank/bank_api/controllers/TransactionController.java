package com.bank.bank_api.controllers;

import com.bank.bank_api.transaction.*;
import com.bank.bank_api.user.User;
import com.bank.bank_api.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("transactions")
@Validated
public class TransactionController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TransactionRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/transaction")
    public ResponseEntity<String> saveTransaction(@RequestBody TransactionRequestDTO data) {
        try {
            // Perform the transaction
            User fromUser = userRepository.findById(data.from_user_id())
                    .orElseThrow(() -> new RuntimeException("Sender user not found"));

            if (fromUser.getBalance().compareTo(data.value()) < 0) {
                throw new RuntimeException("Insufficient balance");
            }

            fromUser.setBalance(fromUser.getBalance().subtract(data.value()));
            userRepository.save(fromUser);

            User toUser = userRepository.findById(data.to_user_id())
                    .orElseThrow(() -> new RuntimeException("Recipient user not found"));

            toUser.setBalance(toUser.getBalance().add(data.value()));
            userRepository.save(toUser);


            // Record the transaction with the current datetime
            Timestamp transactionDatetime = Timestamp.valueOf(LocalDateTime.now());
            Transaction transactionData = new Transaction(data.from_user_id(), data.to_user_id(),
                    data.value(), transactionDatetime);
            repository.save(transactionData);

            return ResponseEntity.ok("Transaction successful");


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }


    @GetMapping("/{id}/recives")
    public ResponseEntity<List<TransactionResponseDTO>> getRecive(@PathVariable Long id) {
        // Fetch transactions by to_user_id
        List<Transaction> transactions = repository.findByToUserId(id);

        // If transactions are found, map them to DTOs and return
        if (!transactions.isEmpty()) {
            List<TransactionResponseDTO> transactionDTOs = transactions.stream()
                    .map(TransactionResponseDTO::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(transactionDTOs);
        } else {
            // No transactions found, return 204 No Content
            return ResponseEntity.noContent().build();
        }
    }


    @GetMapping("/{id}/sents")
    public ResponseEntity<List<TransactionResponseDTO>> getSent(@PathVariable Long id){
        List<Transaction> transactions = repository.findByFromUserId(id);
        if (!transactions.isEmpty()) {
            List<TransactionResponseDTO> transactionDTOs = transactions.stream()
                    .map(TransactionResponseDTO::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(transactionDTOs);
        } else {
            // No transactions found, return 204 No Content
            return ResponseEntity.noContent().build();
        }

    }

}