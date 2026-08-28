package com.biolab.ecommerce.Controllers;

import com.biolab.ecommerce.DTOs.UsuarioDTO;
import com.biolab.ecommerce.Services.UsuarioService;
import com.biolab.ecommerce.entities.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(usuarioService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> mostrarUsuarios() {
        List<UsuarioDTO> lista = usuarioService.mostrarUsuarios();
        return ResponseEntity.ok(lista);
    }


}
