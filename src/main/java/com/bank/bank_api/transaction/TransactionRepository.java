package com.bank.bank_api.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository  extends JpaRepository<Transaction, Long>{

    @Query("SELECT t FROM transactions t WHERE t.to_user_id = :toUserId")
    List<Transaction> findByToUserId(@Param("toUserId") Long toUserId);

    @Query("SELECT t FROM transactions t WHERE t.from_user_id = :fromUserId")
    List<Transaction> findByFromUserId(@Param("fromUserId") Long fromUserId);


}
