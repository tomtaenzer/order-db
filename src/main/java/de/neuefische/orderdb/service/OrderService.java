package de.neuefische.orderdb.service;

import de.neuefische.orderdb.db.OrderDb;
import de.neuefische.orderdb.db.ProductDb;
import de.neuefische.orderdb.model.Order;
import de.neuefische.orderdb.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderService {
  private final ProductDb productsDb;
  private final OrderDb orderDb;

  public OrderService(ProductDb productsDb, OrderDb orderDb) {
    this.productsDb = productsDb;
    this.orderDb = orderDb;
  }

  public Order orderProducts(ArrayList<String> productIdsToOrder) {
    ArrayList<Product> products = new ArrayList<>();

    for (String productId : productIdsToOrder) {
      Product product = productsDb.getProductById(productId);
      products.add(product);
    }
    String uuid = UUID.randomUUID().toString();
    Order order = new Order(uuid, products);

    orderDb.addOrder(order);
    return order;
  }

  public List<Order> listOrders() {
    return orderDb.listOrders();
  }
}
