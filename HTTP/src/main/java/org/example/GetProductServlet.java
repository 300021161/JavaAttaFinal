package org.example;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GetProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (Connection connection = DatabaseConnection.getConnection()) {
            MyConsole myConsole = new MyConsole();
            List<String> allProducts = myConsole.getAllProducts(connection);
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            for (String product : allProducts) {
                out.println("<p>" + product + "</p>");
            }
            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error");
        }
    }

}
