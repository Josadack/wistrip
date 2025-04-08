package com.wistrip.repository;

import com.wistrip.model.DestinoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DestinoRepository extends JpaRepository<DestinoModel, Long> {

    boolean existsById(Long id);
    List<DestinoModel> findByDescricaoContainingIgnoreCase(@Param("descricao")String descricao);
    List<DestinoModel> findByInteressesContainingIgnoreCase(@Param("interesses")String interesses);
}
