package com.wistrip.dtos;

import com.wistrip.model.ViagemModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record DtoUsuario(

        UUID id,

        @NotBlank(message = "O nome não pode ser vazio!")
        String nome,

        @NotBlank(message = "O email não pode ser vazio!")
        @Email(message = "Email inválido")
        String email,

        String senha,

        @NotNull(message = "A data de nascimento não pode ser vazia!")
        Date data_nascimento,

        String telefone,

        String foto
) {}
