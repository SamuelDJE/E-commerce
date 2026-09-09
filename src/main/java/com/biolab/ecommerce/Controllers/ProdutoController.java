package com.biolab.ecommerce.Controllers;

import com.biolab.ecommerce.DTOs.ProdutoDTO;
import com.biolab.ecommerce.Repositories.ProdutoRepository;
import com.biolab.ecommerce.Services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produto")
public class ProdutoController {
        private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @PostMapping
    public ResponseEntity<?> criarProduto(@Valid @RequestBody ProdutoDTO produtoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criarProduto(produtoDTO));
    }

}
