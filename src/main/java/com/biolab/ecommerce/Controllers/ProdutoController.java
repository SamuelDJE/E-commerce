package com.biolab.ecommerce.Controllers;

import com.biolab.ecommerce.DTOs.ProdutoDTO;
import com.biolab.ecommerce.Services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<String> criarProduto(@Valid @RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criarProduto(produtoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> buscarTodosProdutos() {
        return ResponseEntity.ok(produtoService.buscarTodosProdutos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> alterarProdutoPorId(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(produtoService.alterarProdutoPorId(id, produtoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProdutoPorId(@PathVariable Long id) {
        produtoService.deletarProdutoPorId(id);
        return ResponseEntity.noContent().build();
    }
}