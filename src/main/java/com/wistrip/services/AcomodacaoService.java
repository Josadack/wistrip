package com.wistrip.services;

import com.wistrip.model.AcomodacaoModel;
import com.wistrip.repository.AcomodacaoRespository;
import com.wistrip.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcomodacaoService {

    //Construtor
    final AcomodacaoRespository acomodacaoRespository;
    public AcomodacaoService(AcomodacaoRespository acomodacaoRespository){
        this.acomodacaoRespository = acomodacaoRespository;
    }
    @Autowired
    public DestinoRepository destinoRepository;


    public List<AcomodacaoModel> findAll() {
        return acomodacaoRespository.findAll();
    }

    public AcomodacaoModel save(AcomodacaoModel acomodacaoModel) {
        return acomodacaoRespository.save(acomodacaoModel);
    }

    public Optional<AcomodacaoModel> findById(Long id) {
        return acomodacaoRespository.findById(id);
    }

    public boolean existsById(Long id) {
        return acomodacaoRespository.existsById(id);
    }

    public List<AcomodacaoModel> findByDescricao(String descricao) {
        return acomodacaoRespository.findByDescricaoContainingIgnoreCase(descricao);
    }

    public void delete(AcomodacaoModel acomodacaoModel) {
       acomodacaoRespository.delete(acomodacaoModel);
    }
}
