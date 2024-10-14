package com.bank.bank_api.transaction;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record TransactionResponseDTO(Long from_user_id, Long to_user_id, BigDecimal value,
                                     Timestamp transaction_datetime) {
    public  TransactionResponseDTO(Transaction transaction){
        this(transaction.getFrom_user_id(), transaction.getTo_user_id(), transaction.getValue(), transaction.getTransaction_datetime());
    }
}
