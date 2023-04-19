package com.henriquebarucco.estoqueapi.entities.dao;

import com.henriquebarucco.estoqueapi.controllers.sale.dto.RequestSaleDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleDao {

    private Integer quantitySold = 0;
    private Double totalValue = 0.0;
    private Double totalCost = 0.0;
    @JsonProperty("profit")
    private Double profit() {
     return totalValue - totalCost;
    }

    private void addQuantitySold(Integer value) {
        this.quantitySold = this.quantitySold + value;
    }

    private void addTotalValue(Double price, Integer quantity) {
        this.totalValue = this.totalValue + (price * quantity);
    }

    private void addTotalCost(Double unitCost, Integer quantity) {
        this.totalCost = this.totalCost + (quantity * unitCost);
    }

    public void newSale(RequestSaleDto sale, Double unitCost) {
        addQuantitySold(sale.getQuantity());
        addTotalValue(sale.getPrice(), sale.getQuantity());
        addTotalCost(unitCost, sale.getQuantity());
    }
}
