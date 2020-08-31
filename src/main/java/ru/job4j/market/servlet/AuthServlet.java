package ru.job4j.market.servlet;

import ru.job4j.market.persistence.User;
import ru.job4j.market.service.MarketHbmStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

/**
 * ru.job4j.dream.servlet
 *
 * @author romanvohmin
 * @since 04.08.2020
 */

@WebServlet("/auth.do")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sc = req.getSession();
        sc.removeAttribute("user");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = MarketHbmStore.instOf().findUserByEmail(email);
        if (user != null) {
            if (!user.getPassword().equals(password)) {
                req.setAttribute("error", "Не верный email или пароль");
                System.out.println("неверный пароль");
                String error = "Почта или пароль не существует.";
                getServletContext().setAttribute("error", error);
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");

        }
    }
}
