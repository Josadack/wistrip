package com.wistrip.repository;

import com.wistrip.model.AcomodacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AcomodacaoRespository extends JpaRepository<AcomodacaoModel, Long> {

    boolean existsById(Long id);

    List<AcomodacaoModel> findByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
}
