package com.wistrip.services;

import com.wistrip.model.ModelUsuario;
import com.wistrip.repository.RepositoryUsuario;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceUsuario {


    @Autowired
    private RepositoryUsuario repositoryUsuario;

    public List<ModelUsuario> findAll() {
        return this.repositoryUsuario.findAll();
    }

    public List<ModelUsuario> findAllByNomeContainingIgnoreCase(String nome) {
        return this.repositoryUsuario.findAll();
    }

    @Transactional
    public ModelUsuario save(ModelUsuario modelUsuario){
        return repositoryUsuario.save(modelUsuario);
    }


    public Optional<ModelUsuario> findById(UUID id) {
        return repositoryUsuario.findById(id);
    }

    public boolean existsByEmail(@NotBlank(message = "O email não pode ser vazio!") String email) {
        return repositoryUsuario.existsByEmail(email);
    }

    public boolean existsById(UUID id) {
        return repositoryUsuario.existsById(id);
    }
}
