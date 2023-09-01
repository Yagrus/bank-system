package ru.clevertec.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Account {

    private Long id;

    private String iban;

    private String currency;

    private Double balance;

    private LocalDateTime dateOpen;

    private Bank bank;

    private User user;
}
