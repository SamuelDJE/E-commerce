package com.biolab.ecommerce.Controllers;

import com.biolab.ecommerce.DTOs.ItemDoPedidoDTO;
import com.biolab.ecommerce.DTOs.PedidoDTO;
import com.biolab.ecommerce.Services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<?> salveOrder (@RequestBody PedidoDTO pedidoDTO) {
        return ResponseEntity.ok(pedidoService.criarPedido(pedidoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delOrder(@PathVariable long id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.ok("Pedido deletado com sucesso");
    }
    // POST /pedido/{pedidoId}/itens
    @PostMapping("/{pedidoId}/itens")
    public ResponseEntity<ItemDoPedidoDTO> adicionarItem(@PathVariable Long pedidoId,
                                                         @RequestBody ItemDoPedidoDTO dto) {
        return ResponseEntity.ok(pedidoService.adicionarItem(pedidoId, dto));
    }

    // DELETE /pedido/{pedidoId}/itens/{produtoId}
    @DeleteMapping("/{pedidoId}/itens/{produtoId}")
    public ResponseEntity<String> removerItem(@PathVariable Long pedidoId,
                                              @PathVariable Long produtoId) {
        return ResponseEntity.ok(pedidoService.removerItem(pedidoId, produtoId));
    }
}
