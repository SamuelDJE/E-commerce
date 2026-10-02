package com.biolab.ecommerce.Services;

import com.biolab.ecommerce.DTOs.ItemDoPedidoDTO;
import com.biolab.ecommerce.DTOs.PedidoDTO;
import com.biolab.ecommerce.Repositories.ItemDoPedidoRepository;
import com.biolab.ecommerce.Repositories.PedidoRepository;
import com.biolab.ecommerce.Repositories.ProdutoRepository;
import com.biolab.ecommerce.Repositories.UsuarioRepository;
import com.biolab.ecommerce.entities.*;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PedidoService {

    private final PedidoRepository  pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ItemDoPedidoRepository itemDoPedidoRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, ItemDoPedidoRepository itemDoPedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.itemDoPedidoRepository = itemDoPedidoRepository;
        this.produtoRepository = produtoRepository;
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

    //---------PARTE DA TABELA INTERMEDIARIA--------------------------------------------------------------
    public ItemDoPedidoDTO adicionarItem(Long pedidoId, ItemDoPedidoDTO itemDoPedidoDTO) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + pedidoId));
        Produto produto = produtoRepository.findById(itemDoPedidoDTO.getProdutoId()).orElseThrow(()-> new RuntimeException("Produto não encontrado com o ID: " + itemDoPedidoDTO.getProdutoId()));

        ItemDoPedido item = new ItemDoPedido();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(itemDoPedidoDTO.getQuantidade());
        item.setPreco(itemDoPedidoDTO.getPreco());

        ItemDoPedido itemSalvo = itemDoPedidoRepository.save(item);

        ItemDoPedidoDTO res = new ItemDoPedidoDTO();
        res.setPedidoId(pedido.getId());
        res.setProdutoId(produto.getId());
        res.setQuantidade(itemSalvo.getQuantidade());
        res.setPreco(itemSalvo.getPreco());

        return res;
    }
    // --- REMOVER ITEM ---
    public String removerItem(Long pedidoId, Long produtoId) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + pedidoId));

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + produtoId));

        ItemDoPedidoPK pk = new ItemDoPedidoPK();
        pk.setPedido(pedido);
        pk.setProduto(produto);

        if (!itemDoPedidoRepository.existsById(pk)) {
            throw new RuntimeException("Item não encontrado neste pedido");
        }

        itemDoPedidoRepository.deleteById(pk);
        return "Item removido do pedido com sucesso";
    }
}
