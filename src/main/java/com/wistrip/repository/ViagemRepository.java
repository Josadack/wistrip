package com.wistrip.repository;

import com.wistrip.model.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ViagemRepository extends JpaRepository<ViagemModel,Long> {

    List<ViagemModel> findByNomeContainingIgnoreCase(@Param("Nome") String nome);

    @Query("SELECT v FROM ViagemModel v WHERE v.custo_total = :custo_total")
    List<ViagemModel> findByCusto_total(@Param("custo_total") double custo_total);


    boolean existsById(Long id);
}
