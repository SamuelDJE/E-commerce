package com.biolab.ecommerce.DTOs;

import lombok.Data;

@Data
public class ItemDoPedidoDTO {
    private long produtoId;
    private long pedidoId;
    private Integer quantidade;
    private Double preco;
}
