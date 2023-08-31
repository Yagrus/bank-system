package ru.clevertec.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Bank bank;
}
