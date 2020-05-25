package de.neuefische.orderdb;

import de.neuefische.orderdb.db.ProductDb;
import de.neuefische.orderdb.model.Product;

import java.util.ArrayList;
import java.util.List;

public class AppMain {


  public static void main(String[] args) {

    ProductDb productDb = setupProductDb();

    printProducts(productDb);

    // order product
    // print orders
  }

  private static ProductDb setupProductDb() {
    ArrayList<Product> initialProducts = new ArrayList<>();
    initialProducts.add(new Product("1", "Paprika"));
    initialProducts.add(new Product("2", "Tomate"));
    initialProducts.add(new Product("3", "Möhre"));
    return new ProductDb(initialProducts);
  }

  private static void printProducts(ProductDb db) {
    List<Product> products = db.listProducts();
    System.out.println("Products:");
    for (Product product : products) {
      System.out.println(product);
    }
  }

}
