package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user on 7/12/2016.
 */
public class ZeroToHeroServlet extends HttpServlet {
    /**
     * 7.    Create a private method ‘handleRequest’
     * which takes as argument a HttpServletRequest object named ‘req’ and return a String object.
     * <p>
     * 8.	Inside the ‘handleRequest’ method create a String object ‘response’ with the following value:
     * "Hello <b>[firstName] [lastName]</b>! Enjoy Zero To Hero!!!"  where [firstName] and
     * [lastName] will represent the parameters received on request
     * (get them from the HttpServletRequest object).
     * parametrii din getParameter sunt numele inputurilor din form
     */
    private String handleRequest(HttpServletRequest req) {
        String response = "Hello <b>" + req.getParameter("firstName") + " " +
                req.getParameter("lastName") + "</b>! Enjoy Zero To Hero!!!";

        return response;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().write(handleRequest(req));
    }
}
