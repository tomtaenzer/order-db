package de.neuefische.orderdb.db;

import de.neuefische.orderdb.model.Order;
import de.neuefische.orderdb.model.product.NonPerishableProduct;
import de.neuefische.orderdb.model.product.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDbTest {

  @Test
  public void addOrderToDb() {
    //GIVEN
    OrderDb orderDb = new OrderDb();
    ArrayList<Product> products = new ArrayList<>();
    products.add(new NonPerishableProduct("2", "Möhre"));
    Order order = new Order("2", products);

    //WHEN
    orderDb.addOrder(order);

    //THEN
    Order orderById = orderDb.getOrderById("2");

    assertEquals(order, orderById);

  }

  @Test
  public void listOrdersShouldReturnAllOrdersFromDb(){
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

    //WHEN
    List<Order> orders = orderDb.listOrders();

    //THEN
    assertEquals(2, orders.size());
    assertTrue(orders.contains(firstOrder));
    assertTrue(orders.contains(secondOrder));
  }

}
