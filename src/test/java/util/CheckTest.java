package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.clevertec.bank.logic.OutputLogic;
import ru.clevertec.bank.util.Check;
import ru.clevertec.bank.model.Account;
import ru.clevertec.bank.model.Bank;
import ru.clevertec.bank.model.Transaction;
import ru.clevertec.bank.model.User;

import java.util.List;

public class CheckTest {

    Bank bankSender = Bank.builder().name("AlfaBank").build();
    User sender = User.builder().bank(bankSender).build();
    Bank bankReceiver = Bank.builder().name("Clever-Bank").build();
    Account account = Account.builder().iban("BY21 GTFS 0543 0000 0000 0000 0123")
            .bank(bankReceiver).build();
    Transaction transaction = Transaction.builder().id(1332L)
            .type("Перевод").price(234.23).account(account).build();

    @Test
    public void creatCheckNotNaveSize0(){
        List<String> list = Check.create(transaction, sender);
        list.forEach(System.out::println);
        Assertions.assertNotEquals(0, list.size());
        OutputLogic.inputInFile(list);
    }

    @Test
    public void creatCheckContainsBankSender(){
        String expectedWord = "AlfaBank";
        List<String> list = Check.create(transaction, sender);
        Assertions.assertTrue(list.stream()
                .anyMatch(str -> str.contains(expectedWord)));
    }

    @Test
    public void creatCheckContainsBankReceiver(){
        String expectedWord = "Clever-Bank";
        List<String> list = Check.create(transaction, sender);
        Assertions.assertTrue(list.stream()
                .anyMatch(str -> str.contains(expectedWord)));
    }

    @Test
    public void creatCheckContainsIban(){
        String expectedWord = "BY21 GTFS 0543 0000 0000 0000 0123";
        List<String> list = Check.create(transaction, sender);
        Assertions.assertTrue(list.stream()
                .anyMatch(str -> str.contains(expectedWord)));
    }

    @Test
    public void creatCheckContainsId(){
        String expectedWord = "1332";
        List<String> list = Check.create(transaction, sender);
        Assertions.assertTrue(list.stream()
                .anyMatch(str -> str.contains(expectedWord)));
    }

    @Test
    public void creatCheckContainsType(){
        String expectedWord = "Перевод";
        List<String> list = Check.create(transaction, sender);
        Assertions.assertTrue(list.stream()
                .anyMatch(str -> str.contains(expectedWord)));
    }

    @Test
    public void creatCheckContainsPrice(){
        String expectedWord = "243.23";
        List<String> list = Check.create(transaction, sender);
        Assertions.assertTrue(list.stream()
                .anyMatch(str -> str.contains(expectedWord)));
    }

}
