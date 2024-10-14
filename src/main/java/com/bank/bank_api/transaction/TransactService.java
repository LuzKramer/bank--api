package com.bank.bank_api.transaction;

import com.bank.bank_api.user.User;
import com.bank.bank_api.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void transaction(Long fromUserId, Long toUserId, BigDecimal value) {
        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new RuntimeException("Sender user not found"));

        if (fromUser.getBalance().compareTo(value) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        fromUser.setBalance(fromUser.getBalance().subtract(value));
        userRepository.save(fromUser);

        User toUser = userRepository.findById(toUserId)
                .orElseThrow(() -> new RuntimeException("Recipient user not found"));

        toUser.setBalance(toUser.getBalance().add(value));
        userRepository.save(toUser);
    }
}
