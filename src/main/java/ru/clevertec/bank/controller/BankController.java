package ru.clevertec.bank.controller;

import jakarta.servlet.ServletException;
import ru.clevertec.bank.model.Bank;
import ru.clevertec.bank.service.BankService;
import ru.clevertec.bank.service.impl.BankServiceImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/bank")
public class BankController extends HttpServlet {

    BankService service = new BankServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Bank> list = service.getAll();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Список банков:</h1>");
        out.println("<ul>");
        list.forEach(bank -> out.println("<li>" + bank.toString() + "</li>"));
        out.println("</ul>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Bank bank = Bank.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .bic(req.getParameter("bic"))
                .name(req.getParameter("name"))
                .build();

        service.create(bank);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Bank bank = Bank.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .bic(req.getParameter("bic"))
                .name(req.getParameter("name"))
                .build();

        service.update(bank);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Bank bank = Bank.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .build();

        service.delete(bank);
    }
}
