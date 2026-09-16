package com.biolab.ecommerce.Controllers;

import com.biolab.ecommerce.DTOs.CategoriaDTO;
import com.biolab.ecommerce.Services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<String> criarCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.criarCategoria(categoriaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> buscarCategoriaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> buscarTodasCategorias() {
        return ResponseEntity.ok(categoriaService.buscarTodasCategorias());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarCategoriaPorId(@PathVariable Long id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(categoriaService.atualizarCategoriaPorId(id, categoriaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCategoriaPorId(@PathVariable Long id) {
        categoriaService.deletarCategoriaPorId(id);
        return ResponseEntity.noContent().build();
    }
}