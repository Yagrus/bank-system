package ru.clevertec.bank.model;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
}
