package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.bank.model.Bank;
import ru.clevertec.bank.model.User;
import ru.clevertec.bank.service.BankService;
import ru.clevertec.bank.service.UserService;
import ru.clevertec.bank.service.impl.BankServiceImpl;
import ru.clevertec.bank.service.impl.UserServiceImpl;

import java.util.List;

public class UserServiceTest {

    private UserService userService;
    private BankService bankService;

    private final Bank TEST_BANK = Bank.builder().id(10L).name("TestBank").bic("TBBA").build();
    private final User TEST_USER = User.builder().id(45L)
            .firstName("Игорь").lastName("Русаков").middleName("Сергеевич")
            .bank(TEST_BANK).build();


    @BeforeEach
    void init(){
        bankService = new BankServiceImpl();
        userService = new UserServiceImpl();

        bankService.create(TEST_BANK);
        userService.create(TEST_USER);
    }

    @Test
    void findByIdShouldReturnUser(){
        User expectedUser = userService.getById(45L);

        Assertions.assertEquals(TEST_USER, expectedUser);
    }

    @Test
    void findAllShouldContainUser(){
        List<User> list = userService.getAll();

        Assertions.assertTrue(list.stream()
                .anyMatch(bank -> bank.toString().contains("Игорь")));
    }

    @Test
    void createUserShouldBeInDB() {
        User actualUser = User.builder().id(54L)
                .firstName("Ваня").lastName("Полипов").middleName("Геннадьевич")
                .bank(TEST_BANK).build();
        userService.create(actualUser);
        User expectedUser = userService.getById(54L);

        Assertions.assertEquals(actualUser, expectedUser);
    }

    @Test
    void updateUserShouldBeInDB() {
        TEST_USER.setFirstName("Аня");
        userService.update(TEST_USER);
        List<User> list = userService.getAll();

        Assertions.assertTrue(list.stream()
                .anyMatch(bank -> bank.toString().contains("Аня")));

    }

    @Test
    void deletedUserNotShouldInDB(){
        userService.delete(TEST_USER);
        List<User> list = userService.getAll();

        Assertions.assertFalse(list.stream()
                .anyMatch(bank -> bank.toString().contains("Игорь")));
    }
}
