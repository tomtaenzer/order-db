package de.neuefische.orderdb.service;

import de.neuefische.orderdb.db.OrderDb;
import de.neuefische.orderdb.db.ProductDb;
import de.neuefische.orderdb.model.Order;
import de.neuefische.orderdb.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

  @Test
  @DisplayName("orderProducts should  return a new Order")
  public void orderProductsReturnNewOrder(){
    //GIVEN
    ArrayList<Product> initialProducts = new ArrayList<>();
    initialProducts.add(new Product("1", "Paprika"));
    initialProducts.add(new Product("2", "Tomate"));
    initialProducts.add(new Product("3", "Möhre"));
    ProductDb productsDb = new ProductDb(initialProducts);
    OrderService service = new OrderService(productsDb, new OrderDb());

    ArrayList<String> productIdsToOrder = new ArrayList<>();
    productIdsToOrder.add("2");
    productIdsToOrder.add("3");

    //WHEN
    Order order = service.orderProducts(productIdsToOrder);

    //THEN
    assertEquals(2, order.getProducts().size());
    assertTrue(order.getProducts().contains(new Product("2", "Tomate")));
    assertTrue(order.getProducts().contains(new Product("3", "Möhre")));
    assertNotNull(order.getId());
  }

  @Test
  @DisplayName("two orders should have different ids")
  public void twoOrdersShouldHaveDifferentIds(){
    //GIVEN
    ArrayList<Product> initialProducts = new ArrayList<>();
    initialProducts.add(new Product("1", "Paprika"));
    initialProducts.add(new Product("2", "Tomate"));
    initialProducts.add(new Product("3", "Möhre"));
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
  public void orderProductsShouldSaveOrderToDb(){
    //GIVEN
    OrderDb orderDb = new OrderDb();

    ArrayList<Product> initialProducts = new ArrayList<>();
    initialProducts.add(new Product("1", "Paprika"));
    initialProducts.add(new Product("2", "Tomate"));
    initialProducts.add(new Product("3", "Möhre"));
    ProductDb productsDb = new ProductDb(initialProducts);
    OrderService service = new OrderService(productsDb,orderDb);

    ArrayList<String> productIdsToOrder = new ArrayList<>();
    productIdsToOrder.add("2");
    productIdsToOrder.add("3");

    //WHEN
    Order order = service.orderProducts(productIdsToOrder);

    Order result = orderDb.getOrderById(order.getId());

    //THEN
    assertEquals(2, result.getProducts().size());
    assertTrue(result.getProducts().contains(new Product("2", "Tomate")));
    assertTrue(result.getProducts().contains(new Product("3", "Möhre")));
    assertEquals(order.getId(),result.getId());
  }

  @Test
  public void listOrdersShouldReturnAllOrdersFromDb(){
    //GIVEN

    OrderDb orderDb = new OrderDb();
    ArrayList<Product> products = new ArrayList<>();
    products.add(new Product("2", "Möhre"));
    Order firstOrder = new Order("2", products);
    orderDb.addOrder(firstOrder);

    ArrayList<Product> secondProducts = new ArrayList<>();
    secondProducts.add(new Product("1", "Tomate"));
    Order secondOrder = new Order("3", secondProducts);
    orderDb.addOrder(secondOrder);

    OrderService service = new OrderService(null,orderDb);

    //WHEN
    List<Order> orders = service.listOrders();

    //THEN
    assertEquals(2, orders.size());
    assertTrue(orders.contains(firstOrder));
    assertTrue(orders.contains(secondOrder));
  }

}
