package com.cursoplatzi.platzimarketG.domain.repository;

import com.cursoplatzi.platzimarketG.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();

    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int productoId);
    Product save(Product product);

    void delete(int productId);
}
