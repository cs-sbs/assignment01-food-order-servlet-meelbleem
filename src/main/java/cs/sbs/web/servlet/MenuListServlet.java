package cs.sbs.web.servlet;

import cs.sbs.web.DataStore;
import cs.sbs.web.model.MenuItem;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MenuListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");
        List<MenuItem> items = DataStore.searchMenuItems(name);

        out.println("Menu List:");
        out.println();

        if (items.isEmpty()) {
            out.println("No menu items found.");
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            out.println((i + 1) + ". " + item.getName() + " - $" + item.getPrice());
        }
    }
}