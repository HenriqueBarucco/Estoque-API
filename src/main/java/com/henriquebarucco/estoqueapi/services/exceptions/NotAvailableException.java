package com.henriquebarucco.estoqueapi.services.exceptions;

public class NotAvailableException extends RuntimeException {
    public NotAvailableException() {
        super("Não há essa quantidade disponível para venda!");
    }
}
