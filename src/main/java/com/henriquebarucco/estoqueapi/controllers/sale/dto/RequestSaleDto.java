package com.henriquebarucco.estoqueapi.controllers.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestSaleDto {
    Long productId;
    Integer quantity;
    Double price;
    String year;
}
