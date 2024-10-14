package com.bank.bank_api.user;

import java.math.BigDecimal;

public record UserResponseDTO(Long id, String name, BigDecimal balance) {
    public UserResponseDTO(User user){
        this(user.getId(), user.getName(), user.getBalance());
    }
}
