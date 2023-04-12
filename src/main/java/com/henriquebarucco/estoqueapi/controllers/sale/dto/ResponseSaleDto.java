package com.henriquebarucco.estoqueapi.controllers.sale.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
public class ResponseSaleDto {
    Long id;
    ProductDto product;
    Integer quantity;
    LocalDateTime date;
    @JsonProperty("totalValue")
    Double totalValue() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", symbols);
        return Double.parseDouble(df.format(product.price * quantity));
    }
}
