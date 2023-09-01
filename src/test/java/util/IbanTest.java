package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.clevertec.bank.model.Account;
import ru.clevertec.bank.model.Bank;
import ru.clevertec.bank.model.User;
import ru.clevertec.bank.util.Iban;

public class IbanTest {

    User user = User.builder().id(123L).build();
    Bank bank = Bank.builder().bic("GTFS").build();
    Account account = Account.builder()
            .id(543L).user(user).bank(bank).build();

    @Test
    public void createdIbanHaveSize(){
        int expectedSize = 34;
        String iban = Iban.create(account);
        System.out.println(iban);

        Assertions.assertEquals(iban.length(), expectedSize);
    }

    @Test
    public void createdIbanShouldContainsBic(){
        String iban = Iban.create(account);
        Assertions.assertTrue(iban.contains("GTFS"));

    }

    @Test
    public void createdIbanShouldContainsUserId(){
        String iban = Iban.create(account);
        Assertions.assertTrue(iban.contains("123"));

    }

    @Test
    public void createdIbanShouldContainsAccountId(){
        String iban = Iban.create(account);
        Assertions.assertTrue(iban.contains("543"));

    }


}
