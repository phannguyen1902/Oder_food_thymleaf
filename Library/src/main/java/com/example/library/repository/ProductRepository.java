package com.example.library.repository;
import com.example.library.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.is_deleted = false AND p.is_activated = true")
    List<Product> getAllProduct();

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1% OR p.description LIKE %?1%")
    List<Product> findAllByNameOrDescription(String keyword);

    @Query("SELECT p FROM Product p INNER JOIN p.category c WHERE c.name = ?1 AND p.is_activated = true AND p.is_deleted = false")
    List<Product> findAllByCategory(String category);

    @Query(value = "SELECT * FROM products p WHERE p.is_activated = true AND p.is_deleted = false ORDER BY RAND() LIMIT 9", nativeQuery = true)
    List<Product> randomProduct();

    @Query(value = "SELECT * FROM products p WHERE p.is_deleted = false AND p.is_activated = true ORDER BY p.cost_price DESC LIMIT 9", nativeQuery = true)
    List<Product> filterHighProducts();

    @Query(value = "SELECT * FROM products p WHERE p.is_deleted = false AND p.is_activated = true ORDER BY p.cost_price ASC LIMIT 9", nativeQuery = true)
    List<Product> filterLowerProducts();

    @Query(value = "SELECT * FROM products p WHERE p.is_deleted = false AND p.is_activated = true LIMIT 4", nativeQuery = true)
    List<Product> listViewProduct();

    @Query("SELECT p FROM Product p INNER JOIN p.category c WHERE c.id = ?1 AND p.is_activated = true AND p.is_deleted = false")
    List<Product> getProductByCategoryId(Long id);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1% AND p.is_deleted = false")
    List<Product> searchProducts(String keyword);

}
