package com.biolab.ecommerce.Services;

import com.biolab.ecommerce.DTOs.UsuarioDTO;
import com.biolab.ecommerce.Repositories.UsuarioRepository;
import com.biolab.ecommerce.entities.Role;
import com.biolab.ecommerce.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String criar(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setRoles(usuarioDTO.getRoles());
        usuarioRepository.save(usuario);
        return "Usuario criado com sucesso!";
    }

    public List<UsuarioDTO> mostrarUsuarios() {
        return usuarioRepository.findAll().stream().map(usuario -> {
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setEmail(usuario.getEmail());
            usuarioDTO.setNome(usuario.getNome());
            usuarioDTO.setSenha(usuario.getSenha());
            usuarioDTO.setRoles(usuario.getRoles());
            return usuarioDTO;
        }).collect(Collectors.toList());
    }
}
