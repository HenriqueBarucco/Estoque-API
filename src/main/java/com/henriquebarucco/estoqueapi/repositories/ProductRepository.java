package com.henriquebarucco.estoqueapi.repositories;

import com.henriquebarucco.estoqueapi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findFirstByName(String name);
}
