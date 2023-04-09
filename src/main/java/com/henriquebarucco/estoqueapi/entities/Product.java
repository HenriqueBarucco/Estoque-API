package com.henriquebarucco.estoqueapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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

    @JoinColumn(name = "description")
    private String description;

    @JoinColumn(name = "available")
    private Integer available;

    @Column(name = "price")
    private Double price;

    @JsonProperty("total")
    public Double total() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", symbols);
        return Double.parseDouble(df.format(price * available));
    }
}
