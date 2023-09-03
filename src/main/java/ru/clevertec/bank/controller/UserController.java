package ru.clevertec.bank.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.clevertec.bank.aop.annotation.Logging;
import ru.clevertec.bank.model.User;
import ru.clevertec.bank.service.UserService;
import ru.clevertec.bank.service.impl.UserServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/user")
public class UserController extends HttpServlet {

    UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> list = service.getAll();
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Список пользователей:</h1>");
        out.println("<ul>");
        list.forEach(user -> out.println("<li>" + user.toString() + "</li>"));
        out.println("</ul>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = User.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .firstName(req.getParameter("firstName"))
                .middleName(req.getParameter("middleName"))
                .lastName(req.getParameter("lastName"))
                .build();

        service.create(user);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = User.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .firstName(req.getParameter("firstName"))
                .middleName(req.getParameter("middleName"))
                .lastName(req.getParameter("lastName"))
                .build();

        service.update(user);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = User.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .build();

        service.delete(user);
    }

}
