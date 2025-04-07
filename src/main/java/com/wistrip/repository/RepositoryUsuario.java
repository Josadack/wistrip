package com.wistrip.repository;

import com.wistrip.model.ModelUsuario;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RepositoryUsuario extends JpaRepository<ModelUsuario, UUID> {
    public List<ModelUsuario> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

    boolean existsByEmail(@NotBlank(message = "O email não pode ser vazio!") String email);

    boolean existsById(UUID id);
}
