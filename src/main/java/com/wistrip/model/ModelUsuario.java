package com.wistrip.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_usuarios")

public class ModelUsuario extends
        RepresentationModel<ModelUsuario> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank(message = "O nome não pode ser vazio!")
    @Column(nullable = false)
    private String nome;

    @Schema(example = "email@email.com.br")
    @NotBlank(message = "O email não pode ser vazio!")
    @Column(nullable = false)
    private String email;

    @NotNull(message = "A senha não pode ser vazia!")
    @Column(nullable = false)
    private String senha;

    @NotNull(message = "A data não ser vazio")
    @Column(nullable = false)
    private LocalDate data_nascimento;


    private String telefone;

    private String foto;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    @JsonManagedReference
    private List<ViagemModel> viagem;

    public List<ViagemModel> getViagemModel() {
        return viagem;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<ViagemModel> getViagem() {
        return viagem;
    }

    public void setViagem(List<ViagemModel> viagem) {
        this.viagem = viagem;
    }

    public @NotBlank(message = "O nome não pode ser vazio!") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome não pode ser vazio!") String nome) {
        this.nome = nome;
    }

    public @NotBlank(message = "O email não pode ser vazio!") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "O email não pode ser vazio!") String email) {
        this.email = email;
    }

    public @NotNull(message = "A senha não pode ser vazia!") String getSenha() {
        return senha;
    }

    public void setSenha(@NotNull(message = "A senha não pode ser vazia!") String senha) {
        this.senha = senha;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(@NotNull(message = "A data não ser vazio") LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }


    public ModelUsuario(UUID id, String nome, String email, String senha, LocalDate data_nascimento, String telefone, String foto) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.data_nascimento = data_nascimento;
        this.telefone = telefone;
        this.foto = foto;
    }

    public ModelUsuario() { }
}
