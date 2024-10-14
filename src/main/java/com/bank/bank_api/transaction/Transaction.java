package com.bank.bank_api.transaction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "transactions")
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Long from_user_id;
    private Long to_user_id;
    private BigDecimal value;
    private Timestamp transaction_datetime;

    // Constructor with all required fields
    public Transaction(Long from_user_id, Long to_user_id, BigDecimal value, Timestamp transaction_datetime) {
        this.from_user_id = from_user_id;
        this.to_user_id = to_user_id;
        this.value = value;
        this.transaction_datetime = transaction_datetime;
    }
}