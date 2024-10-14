package com.bank.bank_api.user;

import java.math.BigDecimal;

public record UserRequestDTO (String name, BigDecimal balance) {
}
