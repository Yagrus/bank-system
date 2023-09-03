package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.bank.model.Account;
import ru.clevertec.bank.model.Bank;
import ru.clevertec.bank.model.Transaction;
import ru.clevertec.bank.model.User;
import ru.clevertec.bank.service.AccountService;
import ru.clevertec.bank.service.BankService;
import ru.clevertec.bank.service.TransactionService;
import ru.clevertec.bank.service.UserService;
import ru.clevertec.bank.service.impl.AccountServiceImpl;
import ru.clevertec.bank.service.impl.BankServiceImpl;
import ru.clevertec.bank.service.impl.TransactionServiceImpl;
import ru.clevertec.bank.service.impl.UserServiceImpl;
import ru.clevertec.bank.util.Iban;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionServiceTest {

    private UserService userService;
    private BankService bankService;
    private AccountService accountService;
    private TransactionService transactionService;

    private final Bank TEST_BANK = Bank.builder().id(10L).name("TestBank").bic("TBBA").build();
    private final User TEST_USER = User.builder().id(45L)
            .firstName("Игорь").lastName("Русаков").middleName("Сергеевич")
            .bank(TEST_BANK).build();

    private final Account TEST_ACCOUNT = Account.builder()
            .id(123L).balance(100.00).currency("BYN")
            .dateOpen(LocalDateTime.of(2020, 9, 3, 14, 5, 20))
            .bank(TEST_BANK).user(TEST_USER).build();

    private final Transaction TEST_TRANSACTION = Transaction.builder()
            .id(75L).price(35.23).comment("None").type("Перевод").account(TEST_ACCOUNT)
            .date(LocalDateTime.of(2023, 9, 3, 14, 5, 20))
            .build();

    @BeforeEach
    void init(){
        TEST_ACCOUNT.setIban(Iban.create(TEST_ACCOUNT));
        bankService = new BankServiceImpl();
        userService = new UserServiceImpl();
        accountService = new AccountServiceImpl();
        transactionService = new TransactionServiceImpl();

        bankService.create(TEST_BANK);
        userService.create(TEST_USER);
        accountService.create(TEST_ACCOUNT);
        transactionService.create(TEST_TRANSACTION);

    }

    @Test
    void findByIdShouldReturnTransaction(){
        Transaction expectedTransaction = transactionService.getById(75L);

        Assertions.assertEquals(TEST_TRANSACTION, expectedTransaction);
    }

    @Test
    void findAllShouldContainTransaction(){
        List<Transaction> list = transactionService.getAll();

        Assertions.assertTrue(list.stream()
                .anyMatch(bank -> bank.toString().contains("Перевод")));
    }

    @Test
    void createTransactionShouldBeInDB() {
        Transaction actualTransaction = Transaction.builder()
                .id(39L).price(60.23).comment("None").type("Перевод").account(TEST_ACCOUNT)
                .date(LocalDateTime.of(2023, 9, 3, 15, 5, 20))
                .build();
        transactionService.create(actualTransaction);
        Transaction expectedTransaction = transactionService.getById(39L);

        Assertions.assertEquals(actualTransaction, expectedTransaction);
    }

    @Test
    void changeBalanceTest(){
        Transaction transaction = Transaction.builder()
                .id(12L)
                .type("Начисление")
                .price(23.00)
                .date(LocalDateTime.now())
                .account(TEST_ACCOUNT)
                .build();

        transactionService.changeBalance(transaction, 45);
        Account account = accountService.getById(123L);

        Assertions.assertEquals(account.getBalance(),123.00);
    }

    @Test
    void updateTransactionShouldBeInDB() {
        TEST_TRANSACTION.setType("Начисление");
        transactionService.update(TEST_TRANSACTION);
        List<Transaction> list = transactionService.getAll();

        Assertions.assertTrue(list.stream()
                .anyMatch(bank -> bank.toString().contains("Начисление")));

    }

    @Test
    void deletedTransactionNotShouldInDB(){
        transactionService.delete(TEST_TRANSACTION);
        List<Transaction> list = transactionService.getAll();

        Assertions.assertFalse(list.stream()
                .anyMatch(bank -> bank.toString().contains("75")));
    }
}
