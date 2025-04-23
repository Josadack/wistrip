package com.wistrip.repository;

import com.wistrip.model.ModelUsuario;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositoryUsuario extends JpaRepository<ModelUsuario, UUID> {

    public Optional<ModelUsuario> findByEmail(String email);

    public List<ModelUsuario> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

    boolean existsByEmail(@NotBlank(message = "O email não pode ser vazio!") String email);

    boolean existsById(UUID id);
}
