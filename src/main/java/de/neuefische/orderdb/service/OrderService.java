package de.neuefische.orderdb.service;

import de.neuefische.orderdb.db.ProductDb;
import de.neuefische.orderdb.model.Order;
import de.neuefische.orderdb.model.Product;

import java.util.ArrayList;
import java.util.UUID;

public class OrderService {
  private final ProductDb productsDb;

  public OrderService(ProductDb productsDb) {
    this.productsDb = productsDb;
  }

  public Order orderProducts(ArrayList<String> productIdsToOrder) {
    ArrayList<Product> products = new ArrayList<>();

    for (String productId : productIdsToOrder) {
      Product product = productsDb.getProductById(productId);
      products.add(product);
    }
    String uuid = UUID.randomUUID().toString();
    return new Order(uuid, products);
  }
}
