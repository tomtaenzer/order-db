package de.neuefische.orderdb.db;

import de.neuefische.orderdb.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductDbTest {

  @Test
  @DisplayName("List products should return all products")
  public void listProducts(){
    //GIVEN
    ArrayList<Product> products = new ArrayList<>();
    products.add(new Product("1", "Paprika"));
    products.add(new Product("2", "Tomate"));
    products.add(new Product("3", "Möhre"));

    ProductDb db = new ProductDb(products);

    //WHEN
    List<Product> result = db.listProducts();

    //THEN
    assertEquals(result.size(), products.size());
    assertTrue(result.contains(new Product("1", "Paprika")));
    assertTrue(result.contains(new Product("2", "Tomate")));
    assertTrue(result.contains(new Product("3", "Möhre")));
  }

  @Test
  @DisplayName("getProductById should return Product with id")
  public void getProductByIdShouldReturnProduct(){
    //GIVEN
    ArrayList<Product> products = new ArrayList<>();
    products.add(new Product("1", "Paprika"));
    products.add(new Product("2", "Tomate"));
    products.add(new Product("3", "Möhre"));

    ProductDb db = new ProductDb(products);

    //WHEN
    Optional<Product> result = db.getProductById("2");

    //THEN
    assertEquals(new Product("2", "Tomate"), result.get());
  }


  @Test
  @DisplayName("getProductById should return empty Optional when product id not found")
  public void getProductByIdProductNotExists(){
    //GIVEN
    ArrayList<Product> products = new ArrayList<>();
    products.add(new Product("1", "Paprika"));
    products.add(new Product("2", "Tomate"));
    products.add(new Product("3", "Möhre"));

    ProductDb db = new ProductDb(products);

    //WHEN
    Optional<Product> result = db.getProductById("7");

    //THEN
    assertTrue(result.isEmpty());
  }

}
