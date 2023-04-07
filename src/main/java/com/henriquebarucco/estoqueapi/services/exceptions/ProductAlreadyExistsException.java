package com.henriquebarucco.estoqueapi.services.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException() {
        super("Já existe um produto com este nome!");
    }
}
