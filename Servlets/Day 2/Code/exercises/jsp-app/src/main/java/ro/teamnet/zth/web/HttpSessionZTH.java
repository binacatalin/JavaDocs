package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by user on 7/13/2016.
 */
public class HttpSessionZTH extends HttpServlet {
    private String validUsername = "admin", validPassword = "admin";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        resp.setContentType("text/html");

        if (username.equals(validUsername) && password.equals(validPassword)) {
            resp.getWriter().write(username + " ,come to the dark side![" + session.getId() + "]");
        } else {
            session.setAttribute("username", username);
            session.setAttribute("session", session);
            resp.sendRedirect("views/loginFail.jsp");
        }


    }
}
