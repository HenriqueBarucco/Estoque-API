package com.henriquebarucco.estoqueapi.services;

import com.henriquebarucco.estoqueapi.controllers.sale.dto.RequestSaleDto;
import com.henriquebarucco.estoqueapi.entities.Product;
import com.henriquebarucco.estoqueapi.repositories.ProductRepository;
import com.henriquebarucco.estoqueapi.services.exceptions.NotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SaleService {

    @Autowired
    private ProductRepository productRepository;

    public Product sale(RequestSaleDto saleDto) {
        Product product = productRepository.findFirstById(saleDto.getProductId());

        if (product.getAvailable() < saleDto.getQuantity()) {
            throw new NotAvailableException();
        }

        product.sale(saleDto);
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findByIsSoldTrue();
    }
}
