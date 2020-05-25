package de.neuefische.orderdb;

import de.neuefische.orderdb.db.OrderDb;
import de.neuefische.orderdb.db.ProductDb;
import de.neuefische.orderdb.model.Product;
import de.neuefische.orderdb.service.OrderService;
import de.neuefische.orderdb.utils.PrintUtils;

import java.util.ArrayList;

public class AppMain {


  public static void main(String[] args) {

    ProductDb productDb = setupProductDb();
    OrderDb orderDb = new OrderDb();

    PrintUtils.printProducts(productDb);

    // order product
    OrderService orderService = new OrderService(productDb, orderDb);

    ArrayList<String> productIdsToOrder = new ArrayList<>();
    productIdsToOrder.add("2");
    productIdsToOrder.add("3");

    orderService.orderProducts(productIdsToOrder);

    ArrayList<String> secondOrderProducts = new ArrayList<>();
    secondOrderProducts.add("1");
    secondOrderProducts.add("2");
    orderService.orderProducts(secondOrderProducts);

    PrintUtils.printOrders(orderService);
  }

  private static ProductDb setupProductDb() {
    ArrayList<Product> initialProducts = new ArrayList<>();
    initialProducts.add(new Product("1", "Paprika"));
    initialProducts.add(new Product("2", "Tomate"));
    initialProducts.add(new Product("3", "Möhre"));
    return new ProductDb(initialProducts);
  }
}
