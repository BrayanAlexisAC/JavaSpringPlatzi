package com.training.spring.persistence.crud;

import com.training.spring.persistence.entity.ProductModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<ProductModel, Integer> {
    // Methods get back products list about a specific category
    Optional<List<ProductModel>> findByIdCategoryOrderByIdAsc(int idCategory); // Query Method
    @Query(nativeQuery = true, value = "SELECT * FROM PRODUCTOS WHERE id_categoria = ?")
    List<ProductModel> getProductsByCategory(int idCategory);

    // Method get back products list with less stock products and available
    Optional<List<ProductModel>> findByStockQuantityLessThanAndAvailable(int stockQuantity, boolean available);
    @Query(nativeQuery = true, value = "SELECT * FROM PRODUCTS WHERE cantidad_stock = ? AND estado = ?")
    List<ProductModel> getProductsByStockAndAvailable(int stockQuantity, boolean available);
}
