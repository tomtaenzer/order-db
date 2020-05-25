package de.neuefische.orderdb;

import de.neuefische.orderdb.db.ProductDb;
import de.neuefische.orderdb.model.Product;
import de.neuefische.orderdb.utils.PrintUtils;

import java.util.ArrayList;

public class AppMain {


  public static void main(String[] args) {

    ProductDb productDb = setupProductDb();

    PrintUtils.printProducts(productDb);
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
}
