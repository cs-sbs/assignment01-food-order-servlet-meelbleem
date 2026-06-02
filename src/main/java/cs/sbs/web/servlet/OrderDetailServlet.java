package cs.sbs.web.servlet;

import cs.sbs.web.DataStore;
import cs.sbs.web.model.Order;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class OrderDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain; charset=UTF-8");

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.setStatus(404);
            resp.getWriter().println("Error: Order not found");
            return;
        }

        String idStr = pathInfo.substring(1);
        int orderId;
        try {
            orderId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            resp.setStatus(404);
            resp.getWriter().println("Error: Order not found");
            return;
        }

        Order order = DataStore.getOrderById(orderId);
        if (order == null) {
            resp.setStatus(404);
            resp.getWriter().println("Error: Order not found");
            return;
        }

        resp.getWriter().println("Order Detail");
        resp.getWriter().println();
        resp.getWriter().println("Order ID: " + order.getId());
        resp.getWriter().println("Customer: " + order.getCustomer());
        resp.getWriter().println("Food: " + order.getFood());
        resp.getWriter().println("Quantity: " + order.getQuantity());
    }
}