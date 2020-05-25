package de.neuefische.orderdb.utils;

import de.neuefische.orderdb.db.ProductDb;
import de.neuefische.orderdb.model.Order;
import de.neuefische.orderdb.model.product.Product;
import de.neuefische.orderdb.service.OrderService;

import java.util.List;

public class PrintUtils {
  public static void printProducts(ProductDb db) {
    List<Product> products = db.listProducts();
    System.out.println("Products:");
    for (Product product : products) {
      System.out.println(product);
    }
  }

  public static void printOrders(OrderService orderService) {
    // print orders
    List<Order> orderList = orderService.listOrders();
    System.out.println("All Orders");
    for (Order printOrder : orderList) {
      System.out.println(printOrder);
    }
  }
}
