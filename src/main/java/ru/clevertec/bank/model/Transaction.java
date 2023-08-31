package ru.clevertec.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Transaction {

    private Long id;

    private double price;

    private String type;

    private LocalDateTime date;

    private String comment;

    private Account account;

}
