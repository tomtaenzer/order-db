package de.neuefische.orderdb.db;

import de.neuefische.orderdb.model.product.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductDb {

  private final ArrayList<Product> products = new ArrayList<>();

  public ProductDb(List<Product> initialProducts){
      this.products.addAll(initialProducts);
  }

  public List<Product> listProducts(){
    return Collections.unmodifiableList(products);
  }

  public Optional<Product> getProductById(String id){
    for (Product product : products) {
      if(product.getId().equals( id)){
        return Optional.of(product);
      }
    }
    return Optional.empty();
  }

}
