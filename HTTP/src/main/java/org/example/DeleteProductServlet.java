package org.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteProductServlet extends HttpServlet {

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String productIdParam = request.getParameter("productId");
        int productId = (productIdParam != null) ? Integer.parseInt(productIdParam) : 0;
        MyConsole myConsole = new MyConsole();
        myConsole.deleteProduct(productId);

        response.getWriter().println("Product delete!");
    }
}

