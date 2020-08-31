package ru.job4j.market.servlet;

import ru.job4j.market.service.MarketHbmStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.job4j.market.servlet.IndexServlet.LOGGER;

@WebServlet("/status")
public class StatusInvert extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean status = !Boolean.parseBoolean(req.getParameter("status"));
        LOGGER.debug("____________________________________________________id = {} status = {}", id,
                status);
        MarketHbmStore.instOf().changeStatusByPostId(id, status);
        resp.sendRedirect(req.getContextPath() + "/account.do");
//        req.getRequestDispatcher("/account.do").forward(req, resp);
    }
}
