package com.henriquebarucco.estoqueapi.entities.dao;

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

    private Integer quantitySold;
    private Double totalValue;
    private Double totalCost;
    @JsonProperty("profit")
    private Double profit() {
     return totalValue - totalCost;
    }
}
