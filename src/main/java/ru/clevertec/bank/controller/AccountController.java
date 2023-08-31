package ru.clevertec.bank.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.bank.model.Account;
import ru.clevertec.bank.service.AccountService;
import ru.clevertec.bank.service.BankService;
import ru.clevertec.bank.service.UserService;
import ru.clevertec.bank.service.impl.AccountServiceImpl;
import ru.clevertec.bank.service.impl.BankServiceImpl;
import ru.clevertec.bank.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/account")
public class AccountController extends HttpServlet {

    AccountService accountService = new AccountServiceImpl();
    BankService bankService = new BankServiceImpl();
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Account> list = accountService.getAll();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Список счетов:</h1>");
        out.println("<ul>");
        list.forEach(account -> out.println("<li>" + account.toString() + "</li>"));
        out.println("</ul>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Account account = Account.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .currency(req.getParameter("currency"))
                .balance(Double.valueOf(req.getParameter("balance")))
                .dateOpen(LocalDateTime.now())
                .bank(bankService.getById(Long.parseLong(req.getParameter("bank_id"))))
                .user(userService.getById(Long.parseLong(req.getParameter("user_id"))))
                .build();

        accountService.create(account);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = Account.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .currency(req.getParameter("currency"))
                .balance(Double.valueOf(req.getParameter("balance")))
                .dateOpen(LocalDateTime.now())
                .bank(bankService.getById(Long.parseLong(req.getParameter("bank_id"))))
                .user(userService.getById(Long.parseLong(req.getParameter("user_id"))))
                .build();

        accountService.update(account);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = Account.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .build();

        accountService.delete(account);
    }
}
