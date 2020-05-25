package de.neuefische.orderdb.service;

import de.neuefische.orderdb.db.OrderDb;
import de.neuefische.orderdb.db.ProductDb;
import de.neuefische.orderdb.model.Order;
import de.neuefische.orderdb.model.product.NonPerishableProduct;
import de.neuefische.orderdb.model.product.PerishableProduct;
import de.neuefische.orderdb.model.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

  @Test
  @DisplayName("orderProducts should  return a new Order")
  public void orderProductsReturnNewOrder() {
    //GIVEN
    ArrayList<Product> initialProducts = new ArrayList<>();
    initialProducts.add(new PerishableProduct("1", "Paprika", LocalDate.of(2020, 10, 3)));
    initialProducts.add(new NonPerishableProduct("2", "Tomate"));
    initialProducts.add(new PerishableProduct("3", "Möhre",LocalDate.of(2021, 10, 3)));
    ProductDb productsDb = new ProductDb(initialProducts);
    OrderService service = new OrderService(productsDb, new OrderDb());

    ArrayList<String> productIdsToOrder = new ArrayList<>();
    productIdsToOrder.add("2");
    productIdsToOrder.add("3");

    //WHEN
    Order order = service.orderProducts(productIdsToOrder);

    //THEN
    assertEquals(2, order.getProducts().size());
    assertTrue(order.getProducts().contains(new NonPerishableProduct("2", "Tomate")));
    assertTrue(order.getProducts().contains(new PerishableProduct("3", "Möhre",LocalDate.of(2021, 10, 3))));
    assertNotNull(order.getId());
  }

  @Test
  @DisplayName("two orders should have different ids")
  public void twoOrdersShouldHaveDifferentIds() {
    //GIVEN
    ArrayList<Product> initialProducts = new ArrayList<>();
    initialProducts.add(new NonPerishableProduct("1", "Paprika"));
    initialProducts.add(new NonPerishableProduct("2", "Tomate"));
    initialProducts.add(new NonPerishableProduct("3", "Möhre"));
    ProductDb productsDb = new ProductDb(initialProducts);
    OrderService service = new OrderService(productsDb, new OrderDb());

    ArrayList<String> productIdsToOrder = new ArrayList<>();
    productIdsToOrder.add("2");
    productIdsToOrder.add("3");

    //WHEN
    Order firstOrder = service.orderProducts(productIdsToOrder);
    Order secondOder = service.orderProducts(productIdsToOrder);

    //THEN
    assertNotEquals(firstOrder.getId(), secondOder.getId());
  }

  @Test
  @DisplayName("orderProducts should save order to db")
  public void orderProductsShouldSaveOrderToDb() {
    //GIVEN
    OrderDb orderDb = new OrderDb();

    ArrayList<Product> initialProducts = new ArrayList<>();
    initialProducts.add(new NonPerishableProduct("1", "Paprika"));
    initialProducts.add(new NonPerishableProduct("2", "Tomate"));
    initialProducts.add(new NonPerishableProduct("3", "Möhre"));
    ProductDb productsDb = new ProductDb(initialProducts);
    OrderService service = new OrderService(productsDb, orderDb);

    ArrayList<String> productIdsToOrder = new ArrayList<>();
    productIdsToOrder.add("2");
    productIdsToOrder.add("3");

    //WHEN
    Order order = service.orderProducts(productIdsToOrder);

    Order result = orderDb.getOrderById(order.getId());

    //THEN
    assertEquals(2, result.getProducts().size());
    assertTrue(result.getProducts().contains(new NonPerishableProduct("2", "Tomate")));
    assertTrue(result.getProducts().contains(new NonPerishableProduct("3", "Möhre")));
    assertEquals(order.getId(), result.getId());
  }

  @Test
  public void listOrdersShouldReturnAllOrdersFromDb() {
    //GIVEN

    OrderDb orderDb = new OrderDb();
    ArrayList<Product> products = new ArrayList<>();
    products.add(new NonPerishableProduct("2", "Möhre"));
    Order firstOrder = new Order("2", products);
    orderDb.addOrder(firstOrder);

    ArrayList<Product> secondProducts = new ArrayList<>();
    secondProducts.add(new NonPerishableProduct("1", "Tomate"));
    Order secondOrder = new Order("3", secondProducts);
    orderDb.addOrder(secondOrder);

    OrderService service = new OrderService(null, orderDb);

    //WHEN
    List<Order> orders = service.listOrders();

    //THEN
    assertEquals(2, orders.size());
    assertTrue(orders.contains(firstOrder));
    assertTrue(orders.contains(secondOrder));
  }

  @Test
  public void orderNotExistingProductShouldThrowAnException() {
    //GIVEN
    OrderDb orderDb = new OrderDb();

    ArrayList<Product> initialProducts = new ArrayList<>();
    initialProducts.add(new NonPerishableProduct("1", "Paprika"));
    initialProducts.add(new NonPerishableProduct("2", "Tomate"));
    initialProducts.add(new NonPerishableProduct("3", "Möhre"));
    ProductDb productsDb = new ProductDb(initialProducts);
    OrderService service = new OrderService(productsDb, orderDb);

    ArrayList<String> productIdsToOrder = new ArrayList<>();
    productIdsToOrder.add("2");
    productIdsToOrder.add("6");

    //WHEN
    try {
      service.orderProducts(productIdsToOrder);
      fail();
    } catch (IllegalArgumentException e) {
      //THEN
      assertEquals("Product with id 6 not found", e.getMessage());
    }

  }

}
