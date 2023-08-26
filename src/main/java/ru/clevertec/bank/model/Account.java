package ru.clevertec.bank.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Account {

    private Long id;

    private String currency;

    private Double balance;

    private LocalDateTime dateOpen;
}
