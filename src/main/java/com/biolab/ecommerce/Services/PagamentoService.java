package com.biolab.ecommerce.Services;

import com.biolab.ecommerce.DTOs.PagamentoDTO;
import com.biolab.ecommerce.Repositories.PagamentoRepository;
import com.biolab.ecommerce.Repositories.PedidoRepository;
import com.biolab.ecommerce.entities.Pagamento;
import com.biolab.ecommerce.entities.Pedido;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, PedidoRepository pedidoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public String criarPagamento(PagamentoDTO pagamentoDTO) {
        Pedido p = pedidoRepository.findById(pagamentoDTO.getIdPedido()).orElseThrow();
        Pagamento pagamento = new Pagamento();
        pagamento.setMomento(Instant.now());
        pagamento.setPedido(p);
        pagamentoRepository.save(pagamento);
        return "Pagamento realizado com sucesso!";
    }


}
