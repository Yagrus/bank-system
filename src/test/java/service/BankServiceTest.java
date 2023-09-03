package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.bank.model.Bank;
import ru.clevertec.bank.service.BankService;
import ru.clevertec.bank.service.impl.BankServiceImpl;

import java.util.List;

public class BankServiceTest {

    private BankService service;

    private Bank TEST_BANK = Bank.builder().id(10L).name("TestBank").bic("TBBA").build();

    @BeforeEach
    void init(){
        service = new BankServiceImpl();
        service.create(TEST_BANK);
    }

    @Test
    void findByIdShouldReturnBank(){
        Bank expectedBank = service.getById(10L);

        Assertions.assertEquals(TEST_BANK, expectedBank);
    }

    @Test
    void findAllShouldContainBank(){
        List<Bank> list = service.getAll();

        Assertions.assertTrue(list.stream()
                .anyMatch(bank -> bank.toString().contains("TestBank")));
    }

    @Test
    void createBankShouldBeInDB() {
        Bank actualBank = Bank.builder().id(121L).bic("GBGD").name("Bank").build();
        service.create(actualBank);
        Bank expectedBank = service.getById(121L);

        Assertions.assertEquals(actualBank, expectedBank);
    }

    @Test
    void updateBankShouldBeInDB() {
        TEST_BANK.setName("NewBank");
        service.update(TEST_BANK);
        List<Bank> list = service.getAll();

        Assertions.assertTrue(list.stream()
                .anyMatch(bank -> bank.toString().contains("NewBank")));

    }

    @Test
    void deletedBankNotShouldInDB(){
        service.delete(TEST_BANK);
        List<Bank> list = service.getAll();

        Assertions.assertFalse(list.stream()
                .anyMatch(bank -> bank.toString().contains("TestBank")));
    }

}
