package ru.clevertec.bank.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.bank.model.Transaction;
import ru.clevertec.bank.service.AccountService;
import ru.clevertec.bank.service.TransactionService;
import ru.clevertec.bank.service.impl.AccountServiceImpl;
import ru.clevertec.bank.service.impl.TransactionServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/balance")
public class BalanceController extends HttpServlet {

    TransactionService transactionService = new TransactionServiceImpl();
    AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long user_id = Long.valueOf(req.getParameter("user_id"));

        Transaction transaction = Transaction.builder()
                .type(req.getParameter("type"))
                .price(Double.parseDouble(req.getParameter("price")))
                .date(LocalDateTime.now())
                .comment(req.getParameter("comment"))
                .account(accountService.getById(Long.parseLong(req.getParameter("account_id"))))
                .build();

        transactionService.changeBalance(transaction, user_id);

    }
}
