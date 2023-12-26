package org.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Main extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Simple Form</title></head>");
        out.println("<body>");
        out.println("<h2>Simple Form</h2>");
        out.println("<form method=\"post\" action=\"process-form\">");
        out.println("  <label for=\"name\">Name:</label>");
        out.println("  <input type=\"text\" id=\"name\" name=\"name\" required>");
        out.println("  <br>");
        out.println("  <label for=\"email\">Email:</label>");
        out.println("  <input type=\"email\" id=\"email\" name=\"email\" required>");
        out.println("  <br>");
        out.println("  <input type=\"submit\" value=\"Submit\">");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
