package de.neuefische.orderdb.db;

import de.neuefische.orderdb.model.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderDb {

  private final ArrayList<Order> orders = new ArrayList<>();

  public void addOrder(Order order) {
    orders.add(order);
  }

  public Order getOrderById(String id) {
    for (Order order : orders) {
      if(order.getId().equals(id)){
        return order;
      }
    }
    return null;
  }

  public List<Order> listOrders() {
    return Collections.unmodifiableList(orders);
  }
}
