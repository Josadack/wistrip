package com.wistrip.repository;

import com.wistrip.model.AtividadeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AtividadeRespositoy extends JpaRepository<AtividadeModel, Long> {

    @Query("SELECT v FROM AtividadeModel v WHERE v.preco = :preco")
    List<AtividadeModel> findByPrecoContainingIgnoreCase(@Param("preco")double preco);

    List<AtividadeModel> findByNomeContainingIgnoreCase(@Param("nome") String nome);


    boolean existsById(Long id);
}
