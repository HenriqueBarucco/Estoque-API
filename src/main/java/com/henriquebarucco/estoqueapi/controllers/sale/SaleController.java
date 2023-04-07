package com.henriquebarucco.estoqueapi.controllers.sale;

import com.henriquebarucco.estoqueapi.controllers.product.dto.RequestProductDto;
import com.henriquebarucco.estoqueapi.controllers.sale.dto.RequestSaleDto;
import com.henriquebarucco.estoqueapi.entities.Product;
import com.henriquebarucco.estoqueapi.entities.Sale;
import com.henriquebarucco.estoqueapi.services.ProductService;
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
            description = "Reraliza a venda de um produto."
    )
    @PostMapping()
    public ResponseEntity<Sale> saleProduct(@RequestBody RequestSaleDto request) {
        return ResponseEntity.ok().body(saleService.sale(request));
    }

    @Operation(
            summary = "Consultar todas as vendas.",
            description = "Consulta de todas as vendas no banco de dados."
    )
    @GetMapping
    public ResponseEntity<List<Sale>> findAll() {
        List<Sale> list = saleService.findAll();
        return ResponseEntity.ok().body(list);
    }
}

