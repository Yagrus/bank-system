package ru.clevertec.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Bank {

    private long id;
    private String bic;
    private String name;
}
