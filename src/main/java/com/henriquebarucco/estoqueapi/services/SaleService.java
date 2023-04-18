package com.henriquebarucco.estoqueapi.services;

import com.henriquebarucco.estoqueapi.controllers.sale.dto.RequestSaleDto;
import com.henriquebarucco.estoqueapi.entities.Product;
import com.henriquebarucco.estoqueapi.entities.dao.SaleDao;
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

        product.setIsSold(true);
        SaleDao saleDao = product.getSale();

        product.setSale(new SaleDao(
            saleDao.getQuantitySold() + saleDto.getQuantity(),
            saleDao.getTotalValue() + (product.getPrice() * saleDto.getQuantity())
        ));

        product.setAvailable(product.getAvailable() - saleDto.getQuantity());

        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findByIsSoldTrue();
    }
}
