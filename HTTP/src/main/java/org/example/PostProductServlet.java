package org.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String costParam = request.getParameter("cost");
        String idOrderParam = request.getParameter("idOrder");

        double cost = (costParam != null) ? Double.parseDouble(costParam) : 0;
        int idOrder = (idOrderParam != null) ? Integer.parseInt(idOrderParam) : 0;

        MyConsole myConsole = new MyConsole();
        myConsole.addProduct(cost, idOrder);

        response.getWriter().println("Product created successfully.");
    }
}

