package ru.clevertec.bank.util;

import ru.clevertec.bank.model.Account;

public class Iban {

    public static String create(Account account){
        StringBuilder iban = new StringBuilder("BY"); // код страны

        // контрольное число
        String parity = String.valueOf(1 + (int) (Math.random() * 98));
        iban.append("0".repeat(Math.max(0, 2 - parity.length())));
        iban.append(parity).append(" ");

        // код банка bic
        iban.append(account.getBank().getBic()).append(" ");

        // балансовый счёт
        String account_id = String.valueOf(account.getId());
        iban.append("0".repeat(Math.max(0, 4 - account_id.length())));
        iban.append(account_id).append(" ");

        // айди пользователя
        String user_id = String.valueOf(account.getUser().getId());
        for (int i = 1; i <= 16 - account_id.length(); i++) {
            iban.append(0);
            if(i % 4 == 0) iban.append(" ");
        }
        iban.append(user_id);

        return iban.toString();
    }
}
