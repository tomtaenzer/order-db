package de.neuefische.orderdb.utils;

import de.neuefische.orderdb.db.ProductDb;
import de.neuefische.orderdb.model.Product;

import java.util.List;

public class PrintUtils {
  public static void printProducts(ProductDb db) {
    List<Product> products = db.listProducts();
    System.out.println("Products:");
    for (Product product : products) {
      System.out.println(product);
    }
  }
}
