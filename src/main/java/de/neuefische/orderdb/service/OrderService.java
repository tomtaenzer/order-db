package de.neuefische.orderdb.service;

import de.neuefische.orderdb.db.OrderDb;
import de.neuefische.orderdb.db.ProductDb;
import de.neuefische.orderdb.model.Order;
import de.neuefische.orderdb.model.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
      Optional<Product> optionalProduct = productsDb.getProductById(productId);
      if (optionalProduct.isPresent()) {
        products.add(optionalProduct.get());
      } else {
        throw new IllegalArgumentException("Product with id " + productId + " not found");
      }
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
