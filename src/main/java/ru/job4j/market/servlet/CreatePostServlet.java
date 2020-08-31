package ru.job4j.market.servlet;

import ru.job4j.market.persistence.Photo;
import ru.job4j.market.persistence.PostCar;
import ru.job4j.market.persistence.User;
import ru.job4j.market.service.MarketHbmStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

import static ru.job4j.market.servlet.IndexServlet.LOGGER;

@WebServlet("/create.do")
public class CreatePostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<PostCar> postCars = MarketHbmStore.instOf().findAllPostCar();
        req.setAttribute("posts", postCars);
        req.getRequestDispatcher("index.do").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession sc = req.getSession();
        Photo photo = (Photo) sc.getAttribute("photo");
        sc.removeAttribute("photo");
        String mark = req.getParameter("mark");
        String model = req.getParameter("model");
        String color = req.getParameter("color");
        String type = req.getParameter("type");
        String year = req.getParameter("year");
        String mileage = req.getParameter("mileage");
        String bodyType = req.getParameter("bodyType");
        String engine = req.getParameter("engine");
        String price = req.getParameter("price");
        PostCar postCar = new PostCar(
                mark, model, color, type, year, mileage, bodyType, true, price, engine,
                Timestamp.valueOf(LocalDateTime.now()), photo,
                (User) req.getSession().getAttribute("user"));
        LOGGER.debug("---------postCar object in Servlet-------\n{}", postCar);
        MarketHbmStore.instOf().savePostCar(postCar);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
        LOGGER.debug("---------- end of createPostServlet ----------------");
    }
}
