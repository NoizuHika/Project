package com.example.Project.repository;

import com.example.Project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//CRUD-операции (создание, чтение, обновление, удаление)
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);

}
