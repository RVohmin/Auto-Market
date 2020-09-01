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
import static ru.job4j.market.servlet.IndexServlet.LOGGER;

@WebServlet("/select.do")
public class Selector extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String selector = req.getParameter("filter");
        LOGGER.debug("----------------Selector = {}", selector);
        String mark = req.getParameter("mark");
        LOGGER.debug("----------------mark = {}", mark);
        String model = req.getParameter("model");
        LOGGER.debug("----------------model = {}", model);
        Collection<PostCar> posts = null;
        if (selector != null) {
            if (selector.equals("day")) {
                posts = MarketHbmStore.instOf().findPostCarByLastDay();
            }
            if (selector.equals("photo")) {
                posts = MarketHbmStore.instOf().findPostCarWithPhoto();
            }
        } else if (mark != null) {
            posts = MarketHbmStore.instOf().findPostCarByMark(mark);
        } else if (model != null) {
            posts = MarketHbmStore.instOf().findPostCarByModel(model);
        }
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
