package com.henriquebarucco.estoqueapi.services;

import com.henriquebarucco.estoqueapi.controllers.sale.dto.RequestSaleDto;
import com.henriquebarucco.estoqueapi.entities.Product;
import com.henriquebarucco.estoqueapi.entities.Sale;
import com.henriquebarucco.estoqueapi.repositories.ProductRepository;
import com.henriquebarucco.estoqueapi.repositories.SaleRepository;
import com.henriquebarucco.estoqueapi.services.exceptions.NotAvailableException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    public Sale sale(RequestSaleDto saleDto) {
        Product product = productRepository.findFirstById(saleDto.getProductId());
        Sale sale = new Sale(null, product, saleDto.getQuantity(), null);

        LocalDateTime dateTime;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        if (saleDto.getDate() == null) {
            dateTime = LocalDateTime.now();
        } else {
            dateTime = LocalDateTime.parse(saleDto.getDate(), formatter);
        }

        sale.setDate(dateTime);

        //Product product = productRepository.findFirstById(sale.getProductId());

        if (product.getAvailable() < sale.getQuantity()) {
            throw new NotAvailableException();
        }

        product.setAvailable(product.getAvailable() - sale.getQuantity());
        product.setTotal(product.getPrice() * product.getAvailable());

        productRepository.save(product);

        return saleRepository.save(sale);
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }
}
