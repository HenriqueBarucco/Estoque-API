package com.henriquebarucco.estoqueapi.controllers.product;

import com.henriquebarucco.estoqueapi.controllers.product.dto.RequestProductDto;
import com.henriquebarucco.estoqueapi.entities.Product;
import com.henriquebarucco.estoqueapi.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Produtos", description = "Endpoints para os produtos do estoque.")
@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(
            summary = "Adicionar produto no estoque.",
            description = "Adiciona um produto ao banco de dados."
    )
    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody RequestProductDto request) {
        return ResponseEntity.ok().body(productService.insert(request));
    }

    @Operation(
            summary = "Consultar todos os produtos.",
            description = "Consulta de todos os produtos do banco de dados."
    )
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = productService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(
            summary = "Consultar um produto por id.",
            description = "Consulta de um produto por id do banco de dados."
    )
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product obj = productService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @Operation(
            summary = "Remover um produto por id.",
            description = "Remove um produto especifico por id do banco de dados."
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Atualizar um produto no estoque.",
            description = "Atualiza um produto especifico por id do banco de dados."
    )
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateByDate(@PathVariable Long id, @RequestBody RequestProductDto request) {
        productService.update(id, request);
        return ResponseEntity.noContent().build();
    }
}
