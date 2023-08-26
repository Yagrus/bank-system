package ru.clevertec.bank.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {

    private Long id;

    private double price;

    private String type;

    private LocalDateTime date;

    private String comment;

}
