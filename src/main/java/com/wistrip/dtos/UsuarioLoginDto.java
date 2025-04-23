package com.wistrip.dtos;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public record UsuarioLoginDto(
        UUID id,

        String nome,

        String email,

        String senha,

        LocalDate data_nascimento,

        String telefone,

         String foto,

         String toke
) {
}
