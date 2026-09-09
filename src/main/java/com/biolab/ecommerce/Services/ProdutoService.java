package com.biolab.ecommerce.Services;


import com.biolab.ecommerce.DTOs.ProdutoDTO;
import com.biolab.ecommerce.Repositories.CategoriaRepository;
import com.biolab.ecommerce.Repositories.ProdutoRepository;
import com.biolab.ecommerce.entities.Categoria;
import com.biolab.ecommerce.entities.Produto;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public String criarProduto(ProdutoDTO produtoDTO){
        Produto p = new Produto();
        p.setNome(produtoDTO.getNome());
        p.setDescricao(produtoDTO.getDescricao());
        p.setPreco(produtoDTO.getPreco());
        p.setImgUrl(produtoDTO.getImUrl());

        Categoria cat = categoriaRepository.getReferenceById(produtoDTO.getIdCategoria());
        p.getCategorias().add(cat);

        produtoRepository.save(p);
        return "Produto criado com sucesso!" ;


    }
}
