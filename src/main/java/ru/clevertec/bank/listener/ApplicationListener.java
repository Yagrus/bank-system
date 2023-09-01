package ru.clevertec.bank.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.clevertec.bank.util.Percent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class ApplicationListener implements ServletContextListener{

    ScheduledExecutorService service;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Percent(),0, 30, TimeUnit.SECONDS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       service.shutdownNow();
    }
}
