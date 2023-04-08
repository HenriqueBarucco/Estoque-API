package com.henriquebarucco.estoqueapi.services;

import com.henriquebarucco.estoqueapi.controllers.product.dto.RequestProductDto;
import com.henriquebarucco.estoqueapi.entities.Product;
import com.henriquebarucco.estoqueapi.repositories.ProductRepository;
import com.henriquebarucco.estoqueapi.services.exceptions.DatabaseException;
import com.henriquebarucco.estoqueapi.services.exceptions.ProductAlreadyExistsException;
import com.henriquebarucco.estoqueapi.services.exceptions.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> obj = productRepository.findById(id);
        return obj.orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional
    public void delete(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ProductNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Product insert(RequestProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        product.setTotal(product.getPrice() * product.getAvailable());

        if (productRepository.findFirstByName(product.getName()) != null) {
            throw new ProductAlreadyExistsException();
        }

        Product addedProduct = productRepository.save(product);
        return addedProduct;
    }

    public void update(Long id, RequestProductDto productDto) {
        Product obj = modelMapper.map(productDto, Product.class);
        obj.setTotal(obj.getPrice() * obj.getAvailable());

        try {
            Product entity = productRepository.findFirstById(id);
            updateData(entity, obj);
            productRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ProductNotFoundException(id);
        }
    }

    private void updateData(Product entity, Product obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setAvailable(obj.getAvailable());
        entity.setPrice(obj.getPrice());
        entity.setTotal(obj.getAvailable() * obj.getPrice());
    }
}
