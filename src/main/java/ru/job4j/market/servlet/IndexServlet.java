package ru.job4j.market.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.market.persistence.PostCar;
import ru.job4j.market.service.MarketHbmStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

/**
 * ru.job4j.dream.servlet
 *
 * @author romanvohmin
 * @since 29.07.2020
 */
@WebServlet("/index.jsp")
public class IndexServlet extends HttpServlet {
    public final static Logger LOGGER = LoggerFactory.getLogger(IndexServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("------- In IndexServlet ------------{}", 22);
        Collection<PostCar> postCars = MarketHbmStore.instOf().findAllPostCar();
        req.setAttribute("posts", postCars);
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
