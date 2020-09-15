package ru.job4j.market.servlet;

import ru.job4j.market.persistence.PostCar;
import ru.job4j.market.persistence.User;
import ru.job4j.market.service.MarketHbmStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/account.do")
public class AccountPosts extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        HttpSession sc = req.getSession();
        User user = (User) sc.getAttribute("user");
        Collection<PostCar> posts = MarketHbmStore.instOf().findPostCarByUserId(user.getId());
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("account_posts.jsp").forward(req, resp);
    }
}


