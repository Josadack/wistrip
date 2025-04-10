package com.wistrip.services;

import aj.org.objectweb.asm.commons.Remapper;
import com.wistrip.model.AtividadeModel;
import com.wistrip.model.DestinoModel;
import com.wistrip.repository.AtividadeRespositoy;
import com.wistrip.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtividadeService {

    @Autowired
    public AtividadeRespositoy atividadeRespositoy;

    @Autowired
    public DestinoRepository destinoRepository;

    public List<AtividadeModel> findByAll() {
        return atividadeRespositoy.findAll();
    }

    public AtividadeModel save(AtividadeModel atividadeModel) {
        return atividadeRespositoy.save(atividadeModel);
    }

    public Optional<AtividadeModel> findById(Long id) {
        return atividadeRespositoy.findById(id);
    }

    public List<AtividadeModel> findByPreco(double preco) {
        return atividadeRespositoy.findByPrecoContainingIgnoreCase(preco);
    }

    public List<AtividadeModel> findByNome(String nome){
        return atividadeRespositoy.findByNomeContainingIgnoreCase(nome);
    }

    public boolean existsById(Long id) {
        return atividadeRespositoy.existsById(id);
    }

    public void delete(AtividadeModel atividadeModel) {
        atividadeRespositoy.delete(atividadeModel);
    }
}
