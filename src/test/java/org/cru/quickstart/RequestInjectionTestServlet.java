package org.cru.quickstart;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RequestInjectionTest")
public class RequestInjectionTestServlet extends HttpServlet {
    @Inject
    HttpServletRequest request;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)

            throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        writer.println("<html><head /><body>");

        if (request != null)
            writer.println("<h1>request was injected</h1>");

        writer.println("</body></html>");

        writer.close();

    }
}
