package com.biolab.ecommerce.Services;


import com.biolab.ecommerce.DTOs.ProdutoDTO;
import com.biolab.ecommerce.Repositories.CategoriaRepository;
import com.biolab.ecommerce.Repositories.ProdutoRepository;
import com.biolab.ecommerce.entities.Categoria;
import com.biolab.ecommerce.entities.Produto;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ProdutoDTO buscarProdutoPorId(Long id){
        Produto produto = produtoRepository.findById(id).orElseThrow();
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setId(produto.getId());
        produtoDTO.setNome(produto.getNome());
        produtoDTO.setDescricao(produto.getDescricao());
        produtoDTO.setPreco(produto.getPreco());
        produtoDTO.setImUrl(produto.getImgUrl());
        return produtoDTO;
    }
    public List<ProdutoDTO> buscarTodosProdutos(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(produto -> {
            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setId(produto.getId());
            produtoDTO.setNome(produto.getNome());
            produtoDTO.setDescricao(produto.getDescricao());
            produtoDTO.setPreco(produto.getPreco());
            produtoDTO.setImUrl(produto.getImgUrl());
            return produtoDTO;
        }).toList();
    }

    public String deletarProdutoPorId(Long id){
        produtoRepository.deleteById(id);
        return "Produto excluido com sucesso!";
    }

    public String alterarProdutoPorId(Long id, ProdutoDTO produtoDTO){
        Produto produto = produtoRepository.findById(id).orElseThrow();
        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setImgUrl(produtoDTO.getImUrl());

        if(produtoDTO.getIdCategoria() > 0){
            Categoria categoria = categoriaRepository.getReferenceById(produtoDTO.getIdCategoria());
            produto.getCategorias().clear();
            produto.getCategorias().add(categoria);
        }

        produtoRepository.save(produto);
        return "Produto alterado com sucesso!";
    }













}
