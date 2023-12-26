package org.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PutProductServlet extends HttpServlet {
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String productIDParam = request.getParameter("productID");
        String newCostParam = request.getParameter("newCost");
        String newOrderIdParam = request.getParameter("newOrderId");

        int productID = (productIDParam != null) ? Integer.parseInt(productIDParam) : 0;
        double newCost = (newCostParam != null) ? Double.parseDouble(newCostParam) : 0.0;
        int newOrderId = (newOrderIdParam != null) ? Integer.parseInt(newOrderIdParam) : 0;

        MyConsole myConsole = new MyConsole();
        myConsole.updateProduct(productID, newCost, newOrderId);
        response.getWriter().println("Product updated!");
    }
}
