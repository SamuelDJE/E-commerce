package com.biolab.ecommerce.Controllers;

import com.biolab.ecommerce.DTOs.CategoriaDTO;
import com.biolab.ecommerce.Repositories.CategoriaRepository;
import com.biolab.ecommerce.Services.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<?> criarCat(@RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.criarCategoria(categoriaDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCategoriaPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(categoriaService.buscarCategoriaPorId(id));
    }
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> buscarTodasCategorias() {
        List<CategoriaDTO> categorias = categoriaService.buscarTodasCategorias();
        return ResponseEntity.ok(categorias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCategoriaPorId(@PathVariable Long id){
        categoriaService.deletarCategoriaPorId(id);
        return ResponseEntity.noContent().build();
    }
}
