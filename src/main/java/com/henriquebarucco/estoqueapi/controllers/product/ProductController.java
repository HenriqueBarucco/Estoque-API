package com.henriquebarucco.estoqueapi.controllers.product;

import com.henriquebarucco.estoqueapi.controllers.product.dto.ProductDto;
import com.henriquebarucco.estoqueapi.entities.Product;
import com.henriquebarucco.estoqueapi.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto request) {
        return ResponseEntity.ok().body(productService.insert(request));
    }

    /*@Operation(
            summary = "Consultar todos planejamentos.",
            description = "Consulta de todos os planejamentos do banco de dados."
    )
    @GetMapping
    public ResponseEntity<List<Planning>> findAll() {
        List<Planning> list = planningService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Deprecated
    @Operation(
            summary = "Consultar um planejamento por id.",
            description = "Consulta de um planejamento  por id do banco de dados."
    )
    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Planning> findById(@PathVariable Long id) {
        Planning obj = planningService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @Operation(
            summary = "Consultar um planejamento por data.",
            description = "Consulta de um planejamento especifico por data do banco de dados."
    )
    @PostMapping(value = "/date")
    public ResponseEntity<Planning> findByDate(@RequestBody PlanningDto request) {
        Planning planning = planningService.find(request);
        return ResponseEntity.ok().body(planning);
    }

    @Operation(
            summary = "Remover um planejamento por data.",
            description = "Remove um planejamento especifico por data do banco de dados."
    )
    @DeleteMapping()
    public ResponseEntity<Void> deleteByDate(@RequestBody PlanningDto request) {
        planningService.delete(request);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Atualizar um planejamento por data.",
            description = "Atualiza um planejamento especifico por data do banco de dados."
    )
    @PutMapping()
    public ResponseEntity<Void> updateByDate(@RequestBody PlanningTimetableDto request) {
        planningService.update(request);
        return ResponseEntity.noContent().build();
    }*/
}
