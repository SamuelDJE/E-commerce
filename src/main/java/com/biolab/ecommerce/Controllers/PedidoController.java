package com.biolab.ecommerce.Controllers;

import com.biolab.ecommerce.DTOs.PedidoDTO;
import com.biolab.ecommerce.Services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
