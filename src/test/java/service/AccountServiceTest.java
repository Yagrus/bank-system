package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.bank.model.Account;
import ru.clevertec.bank.model.Bank;
import ru.clevertec.bank.model.User;
import ru.clevertec.bank.service.AccountService;
import ru.clevertec.bank.service.BankService;
import ru.clevertec.bank.service.UserService;
import ru.clevertec.bank.service.impl.AccountServiceImpl;
import ru.clevertec.bank.service.impl.BankServiceImpl;
import ru.clevertec.bank.service.impl.UserServiceImpl;
import ru.clevertec.bank.util.Iban;

import java.time.LocalDateTime;
import java.util.List;

public class AccountServiceTest {

    private UserService userService;
    private BankService bankService;
    private AccountService accountService;

    private final Bank TEST_BANK = Bank.builder().id(10L).name("TestBank").bic("TBBA").build();
    private final User TEST_USER = User.builder().id(45L)
            .firstName("Игорь").lastName("Русаков").middleName("Сергеевич")
            .bank(TEST_BANK).build();

    private final Account TEST_ACCOUNT = Account.builder()
            .id(123L).balance(100.00).currency("BYN")
            .dateOpen(LocalDateTime.of(2020, 9, 3, 14, 5, 20))
            .bank(TEST_BANK).user(TEST_USER).build();

    @BeforeEach
    void init(){
        TEST_ACCOUNT.setIban(Iban.create(TEST_ACCOUNT));
        bankService = new BankServiceImpl();
        userService = new UserServiceImpl();
        accountService = new AccountServiceImpl();

        bankService.create(TEST_BANK);
        userService.create(TEST_USER);
        accountService.create(TEST_ACCOUNT);

    }

    @Test
    void findByIdShouldReturnAccount(){
        Account expectedAccount = accountService.getById(123L);

        Assertions.assertEquals(TEST_ACCOUNT, expectedAccount);
    }

    @Test
    void findAllShouldContainAccount(){
        List<Account> list = accountService.getAll();

        Assertions.assertTrue(list.stream()
                .anyMatch(bank -> bank.toString().contains("BYN")));
    }

    @Test
    void createAccountShouldBeInDB() {
        Account actualAccount = Account.builder().id(54L)
                .id(165L).balance(200.00).currency("BYN")
                .dateOpen(LocalDateTime.of(2019, 8, 13, 16, 7, 20))
                .bank(TEST_BANK).user(TEST_USER).build();
        accountService.create(actualAccount);
        Account expectedAccount = accountService.getById(165L);

        Assertions.assertEquals(actualAccount, expectedAccount);
    }

    @Test
    void updateAccountShouldBeInDB() {
        TEST_ACCOUNT.setCurrency("RUB");
        accountService.update(TEST_ACCOUNT);
        List<Account> list = accountService.getAll();

        Assertions.assertTrue(list.stream()
                .anyMatch(bank -> bank.toString().contains("RUB")));

    }

    @Test
    void deletedAccountNotShouldInDB(){
        accountService.delete(TEST_ACCOUNT);
        List<Account> list = accountService.getAll();

        Assertions.assertFalse(list.stream()
                .anyMatch(bank -> bank.toString().contains("RUB")));
    }
}
