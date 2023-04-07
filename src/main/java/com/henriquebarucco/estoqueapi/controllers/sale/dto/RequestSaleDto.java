package com.henriquebarucco.estoqueapi.controllers.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestSaleDto {
    Long productId;
    Integer quantity;
    String date;
}
