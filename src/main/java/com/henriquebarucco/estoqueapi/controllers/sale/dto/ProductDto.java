package com.henriquebarucco.estoqueapi.controllers.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    Long id;
    String name;
    String description;
    Double price;
}
