package cs.sbs.web.servlet;

import cs.sbs.web.DataStore;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class OrderCreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain; charset=UTF-8");

        String customer = req.getParameter("customer");
        String food = req.getParameter("food");
        String quantityStr = req.getParameter("quantity");

        if (customer == null || customer.trim().isEmpty()) {
            resp.setStatus(400);
            resp.getWriter().println("Error: missing parameter: customer");
            return;
        }
        if (food == null || food.trim().isEmpty()) {
            resp.setStatus(400);
            resp.getWriter().println("Error: missing parameter: food");
            return;
        }
        if (quantityStr == null || quantityStr.trim().isEmpty()) {
            resp.setStatus(400);
            resp.getWriter().println("Error: missing parameter: quantity");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr.trim());
        } catch (NumberFormatException e) {
            resp.setStatus(400);
            resp.getWriter().println("Error: quantity must be a valid number");
            return;
        }

        if (quantity <= 0) {
            resp.setStatus(400);
            resp.getWriter().println("Error: quantity must be a positive number");
            return;
        }

        int orderId = DataStore.createOrder(customer.trim(), food.trim(), quantity);
        resp.getWriter().println("Order Created: " + orderId);
    }
}