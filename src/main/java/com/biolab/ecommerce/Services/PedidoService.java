package com.biolab.ecommerce.Services;

import com.biolab.ecommerce.DTOs.PedidoDTO;
import com.biolab.ecommerce.Repositories.PedidoRepository;
import com.biolab.ecommerce.Repositories.UsuarioRepository;
import com.biolab.ecommerce.entities.Pedido;
import com.biolab.ecommerce.entities.StatusPedido;
import com.biolab.ecommerce.entities.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PedidoService {

    private final PedidoRepository  pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public String criarPedido(PedidoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdCliente()).orElseThrow();
        Pedido pedido = new Pedido();
        pedido.setCliente(usuario);
        pedido.setMomento(Instant.now());
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        pedidoRepository.save(pedido);
        return "Pedido criado com sucesso";
    }

    public String deletarPedido(long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow();
        pedidoRepository.deleteById(id);
        return "Pedido deletado com sucesso";
    }
}
