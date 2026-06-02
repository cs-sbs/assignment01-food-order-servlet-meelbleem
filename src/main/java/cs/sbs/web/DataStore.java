package cs.sbs.web;

import cs.sbs.web.model.MenuItem;
import cs.sbs.web.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DataStore {

    private static final List<MenuItem> menuItems = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();
    private static final AtomicInteger nextOrderId = new AtomicInteger(1001);

    static {
        menuItems.add(new MenuItem("Fried Rice", 8));
        menuItems.add(new MenuItem("Fried Noodles", 9));
        menuItems.add(new MenuItem("Burger", 10));
    }

    public static List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public static List<MenuItem> searchMenuItems(String name) {
        if (name == null || name.isEmpty()) {
            return new ArrayList<>(menuItems);
        }
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(item);
            }
        }
        return result;
    }

    public static int createOrder(String customer, String food, int quantity) {
        int id = nextOrderId.getAndIncrement();
        orders.add(new Order(id, customer, food, quantity));
        return id;
    }

    public static Order getOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }
}