package com.henriquebarucco.estoqueapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Product.class)
    private Product product;

    @JoinColumn(name = "quantity")
    private Integer quantity;

    @JoinColumn(name = "date")
    private LocalDateTime date;
}
