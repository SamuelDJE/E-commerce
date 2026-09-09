package com.biolab.ecommerce.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private long id;
    @NotBlank
    private String nome;
    private String descricao;
    @NotNull
    private double preco;
    private String imUrl;
    private long idCategoria;

    public ProdutoDTO(String nome, String descricao, double preco, String imUrl, long idCategoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.imUrl = imUrl;
        this.idCategoria = idCategoria;
    }
}
