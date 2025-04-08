package com.wistrip.dtos;

import com.wistrip.model.DestinoModel;
import com.wistrip.model.ModelUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ViagemDto(
        Long id,

        @NotBlank

        String nome,


        LocalDate data_inicio,

        LocalDate data_fim,

        @NotBlank

        String estilo_viagem,

        @NotBlank

        String companhia,

        @NotBlank

        String status,

        @NotNull
        double custo_total,

        @NotBlank
        String moeda,

       @NotNull
        ModelUsuario usuario,

        DestinoModel destino
) {
}
