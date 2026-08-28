package com.biolab.ecommerce.DTOs;

import com.biolab.ecommerce.entities.Role;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String nome;
    @NotBlank @Email
    @Size(max =150)
    private String email;
    @Size(max =20, message = "O máximo são 20 caracteres")
    private String telefone;
    @NotBlank @Size(min = 6) @Column(length=150)
    private String senha;
    private Role roles;

    public UsuarioDTO(String nome, String email, String telefone, String senha, Role roles) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.roles = roles;
    }
}
