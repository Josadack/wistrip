package com.wistrip.dtos;

import com.wistrip.model.AcomodacaoModel;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record DestinoDto(
        Long id,

        @NotBlank
        String nome,

        @NotBlank
        @Column(length = 100)
        String descricao,

        @Column(length = 100)
        String interesses

) {
}
