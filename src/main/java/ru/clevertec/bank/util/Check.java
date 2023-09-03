package ru.clevertec.bank.util;

import ru.clevertec.bank.model.Transaction;
import ru.clevertec.bank.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The Check class creates a new check with information about the transaction.
 */
public class Check {

    private static Date dateNow = new Date();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a zzz");
    private static List<String> check = new ArrayList<>(List.of(
            "+-----------------------------------------------------+",
            "|                    Банковский чек                   |",
            "|Чек:              ",
            "|" + dateFormat.format(dateNow) + "        ",
            "|Тип транзакции:   ",
            "|Банк отправителя: ",
            "|Банк получателя:  ",
            "|Счёт получателя:  ",
            "|Сумма:            ",
            "+-----------------------------------------------------+"
    ));

    public static List<String> create(Transaction transaction, User user){
        List<String> value = List.of(
                String.valueOf(transaction.getId()),
                timeFormat.format(dateNow),
                transaction.getType(),
                user.getBank().getName(),
                transaction.getAccount().getBank().getName(),
                transaction.getAccount().getIban(),
                String.valueOf(transaction.getPrice())

        );
        AtomicInteger startIndex = new AtomicInteger(2);
        value.forEach(val -> setValueInList(startIndex.getAndIncrement(), val));

        return check;
    }

    private static void setValueInList(int index, String value){
        StringBuilder voidStr = new StringBuilder();
        voidStr.append(" ".repeat(Math.max(0, 35 - value.length())));
        check.set(index, check.get(index) + voidStr + value + "|");
    }
}
