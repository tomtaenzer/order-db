package de.neuefische.orderdb.model;

import de.neuefische.orderdb.model.product.Product;

import java.util.ArrayList;
import java.util.Objects;

public class Order {
  private String id;
  private ArrayList<Product> products;

  public Order(String id, ArrayList<Product> products) {
    this.id = id;
    this.products = products;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ArrayList<Product> getProducts() {
    return products;
  }

  public void setProducts(ArrayList<Product> products) {
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return Objects.equals(id, order.id) &&
        Objects.equals(products, order.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, products);
  }

  @Override
  public String toString() {
    return "Order{" +
        "id='" + id + '\'' +
        ", products=" + products +
        '}';
  }
}
