package com.henriquebarucco.estoqueapi.repositories;

import com.henriquebarucco.estoqueapi.entities.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findFirstByName(String name);

    Product findFirstById(Long id);
    
    List<Product> findByIsSoldTrue();

    @Query("SELECT SUM(s.profit) FROM Product p JOIN p.sales s")
    Double getTotalProfit();
}
