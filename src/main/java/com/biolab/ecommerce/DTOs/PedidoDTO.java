package com.biolab.ecommerce.DTOs;

import com.biolab.ecommerce.entities.StatusPedido;
import lombok.*;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private  long id;
    private Instant momento;
    private StatusPedido status;
    private long idCliente;

    public PedidoDTO( long idCliente)//Só mantenho  como parametro oque eu quero que seja digitado pelo usuario
    {
        this.momento = Instant.now();
        this.status = StatusPedido.AGUARDANDO_PAGAMENTO;
        this.idCliente = idCliente;
    }
}
