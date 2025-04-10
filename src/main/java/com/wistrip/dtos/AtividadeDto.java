package com.wistrip.dtos;

import com.wistrip.model.DestinoModel;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtividadeDto(

        Long id,


        @NotBlank
        @Column(nullable = false, length = 255)
        String nome,


        @NotBlank
        @Column(nullable = false, length = 255)
        String descricao,

        @NotBlank
        @Column(nullable = false, length = 255)
        String localizacao,


        @NotNull
        @Column(nullable = false)
        double preco,

        DestinoModel destino
) {
}
