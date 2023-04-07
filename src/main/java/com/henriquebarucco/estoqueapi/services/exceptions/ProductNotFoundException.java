package com.henriquebarucco.estoqueapi.services.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Object id) {
        super("Produto não encontrado. Id " + id);
    }
}
