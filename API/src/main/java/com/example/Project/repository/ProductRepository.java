package com.example.Project.repository;

import com.example.Project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);

    List<Product> findByNameContaining(String name);

    Optional<Product> findByNameAndBialkaAndTluszczeAndWeglowodaneAndGrams(String name, Double bialka, Double tluszcze, Double weglowodane, Double grams);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE CONCAT('%', LOWER(:name), '%')")
    List<Product> findByNameContainingIgnoreCase(@Param("name") String name);

}
