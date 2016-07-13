package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 7/13/2016.
 */
public class HelloWorldServletForward extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String obj = (String) req.getAttribute("testAttribute");
//        req.getRequestDispatcher().include(req, resp);
        resp.setContentType("text/html");
        resp.getWriter().write("Hello <b>" + req.getParameter("user") +
                "</b> from the Forward Servlet" + obj);

    }

}
