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
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/transaction")
public class TransactionController extends HttpServlet {

    TransactionService transactionService = new TransactionServiceImpl();
    AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Transaction> list = transactionService.getAll();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Список транзакций:</h1>");
        out.println("<ul>");
        list.forEach(transaction -> out.println("<li>" + transaction.toString() + "</li>"));
        out.println("</ul>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Transaction transaction = Transaction.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .price(Double.parseDouble(req.getParameter("price")))
                .type(req.getParameter("type"))
                .date(LocalDateTime.now())
                .comment(req.getParameter("comment"))
                .account(accountService.getById(Long.parseLong(req.getParameter("account_id"))))
                .build();

        transactionService.create(transaction);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Transaction transaction = Transaction.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .price(Double.parseDouble(req.getParameter("price")))
                .type(req.getParameter("type"))
                .date(LocalDateTime.now())
                .comment(req.getParameter("comment"))
                .account(accountService.getById(Long.parseLong(req.getParameter("account_id"))))
                .build();

        transactionService.update(transaction);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Transaction transaction = Transaction.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .build();

        transactionService.delete(transaction);
    }
}
