package com.henriquebarucco.estoqueapi.controllers.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDto {
    String name;
    String model;
    String measure;
    Integer available;
    Double price;
}
