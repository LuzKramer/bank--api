package com.bank.bank_api.transaction;

import java.math.BigDecimal;

public record TransactionRequestDTO(Long from_user_id, Long to_user_id, BigDecimal value) {
}
