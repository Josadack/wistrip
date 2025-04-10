package com.wistrip.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_atividades")
public class AtividadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String nome;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String descricao;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String localizacao;

    @NotNull
    @Column(nullable = false)
    private double preco;

    @ManyToOne
    @JsonIgnoreProperties("atividade")
    private DestinoModel destino;

    public DestinoModel getDestino() {
        return destino;
    }

    public void setDestino(DestinoModel destino) {
        this.destino = destino;
    }

    //Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }


}
