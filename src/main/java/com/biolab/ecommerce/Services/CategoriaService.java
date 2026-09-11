package com.biolab.ecommerce.Services;

import com.biolab.ecommerce.DTOs.CategoriaDTO;
import com.biolab.ecommerce.Repositories.CategoriaRepository;
import com.biolab.ecommerce.entities.Categoria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public String criarCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaDTO.getNome());
        categoriaRepository.save(categoria);
        return "Categoria criada com sucesso!";
    }

    public CategoriaDTO buscarCategoriaPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow();
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoriaDTO.getId());
        categoriaDTO.setNome(categoria.getNome());
        return categoriaDTO;
    }

    public String deletarCategoriaPorId(Long id) {
        categoriaRepository.deleteById(id);
        return "Categoria deletada com sucesso!";
    }

    public List<CategoriaDTO> buscarTodasCategorias() {
        // 1. Busca todas as categorias no banco de dados
        List<Categoria> categorias = categoriaRepository.findAll();

        // 2. Mapeia a lista de entidades para uma lista de DTOs
        return categorias.stream()
                .map(categoria -> {
                    CategoriaDTO dto = new CategoriaDTO();
                    dto.setId(categoria.getId());
                    dto.setNome(categoria.getNome());
                    return dto;
                })
                .toList(); // Use .collect(Collectors.toList()) se estiver no Java 15 ou inferior
    }

}
