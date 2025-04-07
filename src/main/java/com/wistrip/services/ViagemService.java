package com.wistrip.services;

import com.wistrip.dtos.ViagemDto;
import com.wistrip.model.ModelUsuario;
import com.wistrip.model.ViagemModel;
import com.wistrip.repository.RepositoryUsuario;
import com.wistrip.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepository viagemRepository;

    @Autowired
    private RepositoryUsuario repositoryUsuario;




    public ViagemModel save(ViagemModel viagemModel) {
        return viagemRepository.save(viagemModel);
    }


    public Optional<ViagemModel> findById(Long id) {
        return viagemRepository.findById(id);
    }

    public List<ViagemModel> findAll() {
        return viagemRepository.findAll();
    }

    public List<ViagemModel> findByNome(String nome) {
        return viagemRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<ViagemModel> findByCustoTotal(double custo_total){
        return viagemRepository.findByCusto_total(custo_total);
    }

    public boolean existsById(Long id) {
        return viagemRepository.existsById(id);
    }

    public void delete(ViagemModel viagemModel) {
        viagemRepository.delete(viagemModel);
    }
}
