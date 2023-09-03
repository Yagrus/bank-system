package ru.clevertec.bank.util;

import org.yaml.snakeyaml.Yaml;
import ru.clevertec.bank.dao.AccountRepository;
import ru.clevertec.bank.model.Account;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * The Percent class that is responsible for calculating interest
 * on the balance every 30 seconds in the end of month.
 */
public class Percent implements Runnable{

    AccountRepository repository;

    private final String CONFIG_PATH = "D:\\projects\\bank\\src\\main\\resources\\application.yaml";

    public Percent(){
        repository = new AccountRepository();
    }

    @Override
    public void run() {
        if(isEndOfMonth()) {
            try {
                Map<String, Map<String, Object>> data = new Yaml()
                        .load(new FileReader(CONFIG_PATH));
                int percent = (int) data.get("bank").get("percent");
                List<Account> accounts = repository.findAll();

                for (Account account : accounts) {
                    double balance = account.getBalance();
                    double interest = balance * percent / 100;
                    account.setBalance(balance + interest);
                    repository.updateBalance(account);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isEndOfMonth(){
        Calendar now = Calendar.getInstance();
        int dayOfMonth = now.get(Calendar.DAY_OF_MONTH);
        int lastDayOfMonth = now.getActualMaximum(Calendar.DAY_OF_MONTH);

        return dayOfMonth == lastDayOfMonth;
    }
}
