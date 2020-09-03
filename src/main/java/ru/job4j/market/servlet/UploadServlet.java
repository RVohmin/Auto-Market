package ru.job4j.market.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.market.persistence.Photo;
import ru.job4j.market.service.MarketHbmStore;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * ru.job4j.dream.servlet
 *
 * @author romanvohmin
 * @since 01.08.2020
 */
@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Photo photo = null;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("auto-images");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (item.getName().equals("")) {
                    req.getRequestDispatcher("/createPost.jsp").forward(req, resp);
                }
                photo = new Photo(0, item.getName());
                int photoId = MarketHbmStore.instOf().savePhoto(photo);
                photo.setId(photoId);
                if (!item.isFormField()) {
                    File file = new File(folder + File.separator + item.getName());

                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        if (photo != null) {
            req.setAttribute("photoId", String.valueOf(photo.getId()));
            HttpSession sc = req.getSession();
            sc.setAttribute("photo", photo);
        }
        req.getRequestDispatcher("/createPost.jsp").forward(req, resp);
    }
}