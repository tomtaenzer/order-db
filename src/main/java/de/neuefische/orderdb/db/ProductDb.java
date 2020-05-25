package de.neuefische.orderdb.db;

import de.neuefische.orderdb.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductDb {

  private final ArrayList<Product> products = new ArrayList<>();

  public ProductDb(List<Product> initialProducts){
      this.products.addAll(initialProducts);
  }

  public List<Product> listProducts(){
    return Collections.unmodifiableList(products);
  }

}
