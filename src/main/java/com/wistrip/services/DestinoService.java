package com.wistrip.services;

import com.wistrip.model.DestinoModel;
import com.wistrip.repository.DestinoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinoService {
    
    /* Criação do construtor */
    final DestinoRepository destinoRepository;
    
    public DestinoService(DestinoRepository destinoRepository){

        this.destinoRepository = destinoRepository;
    }

    @Transactional
    public DestinoModel save(DestinoModel destinoModel) {
        return destinoRepository.save(destinoModel);
    }

    public List<DestinoModel> findAll() {
        return destinoRepository.findAll();
    }

    public Optional<DestinoModel> findById(Long id) {
        return destinoRepository.findById(id);
    }

    public List<DestinoModel> findByDescricao(String  descricao){
        return destinoRepository.findByDescricaoContainingIgnoreCase(descricao);
    }


    public List<DestinoModel> findByInteresses(String interesses) {
        return destinoRepository.findByInteressesContainingIgnoreCase(interesses);
    }

    public void delete(DestinoModel destinoModel) {
        destinoRepository.delete(destinoModel);
    }
}
