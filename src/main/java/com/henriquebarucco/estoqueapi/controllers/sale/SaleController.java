package com.henriquebarucco.estoqueapi.controllers.sale;

import com.henriquebarucco.estoqueapi.controllers.sale.dto.RequestSaleDto;
import com.henriquebarucco.estoqueapi.entities.Product;
import com.henriquebarucco.estoqueapi.services.SaleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Vendas", description = "Endpoints para realizar as vendas.")
@RestController
@RequestMapping(path = "/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Operation(
            summary = "Vender um produto do estoque.",
            description = "Realiza a venda de um produto."
    )
    @PostMapping()
    public ResponseEntity<Product> saleProduct(@RequestBody RequestSaleDto request) {
        Product product = saleService.sale(request);
        return ResponseEntity.ok().body(product);
    }

    @Operation(
            summary = "Consultar todas as vendas.",
            description = "Consulta de todas as vendas no banco de dados."
    )
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = saleService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(
        summary = "Resultado de todas as vendas.",
        description = "Valor de lucro de todas as vendas."
    )
    @GetMapping(value = "/status")
    public ResponseEntity<Double> status() {
        Double profit = saleService.status();
        return ResponseEntity.ok().body(profit);
    }
}

