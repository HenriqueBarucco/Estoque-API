package com.henriquebarucco.estoqueapi.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.henriquebarucco.estoqueapi.controllers.sale.dto.RequestSaleDto;
import com.henriquebarucco.estoqueapi.entities.dao.SaleDao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "tb_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "model")
    private String model;

    @JoinColumn(name = "measure")
    private String measure;

    @JoinColumn(name = "available")
    private Integer available;

    @Column(name = "price")
    private Double price;

    @Column(name = "isSold")
    private Boolean isSold = false;

    @JoinColumn(name = "sale")
    private SaleDao sale = new SaleDao();

    @ElementCollection
    @Column(name = "years")
    private List<String> years = new ArrayList<>();

    @JsonProperty("total")
    public Double total() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", symbols);
        return Double.parseDouble(df.format(price * available));
    }

    public void sale(RequestSaleDto saleDto) {
        if (!this.years.contains(saleDto.getYear())) {
            this.years.add(saleDto.getYear());
        }
        this.available = this.available - saleDto.getQuantity();
        this.sale.newSale(saleDto, this.price);
        this.isSold = true;
    }
}
