package com.biolab.ecommerce.entities;


import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


//Usa essa anotação(@Embeddable) porque ela nao vai virar tabela
// no banco de dados, e toda classe que tiver(@Entity) se torna uma tabela
// no banco de dados.
@Embeddable
@Getter
@Setter
public class ItemDoPedidoPK {

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
}
