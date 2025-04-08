package com.wistrip.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tb_viagens")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ViagemModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100)
    private String nome;

    @UpdateTimestamp
    private LocalDate data_inicio;

    @UpdateTimestamp
    private LocalDate data_fim;

    @NotBlank
    @Column(nullable = false, length = 210)
    private String estilo_viagem;

    @NotBlank
    @Column(nullable = false, length = 210)
    private String companhia;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String status;

    @NotNull
    private double custo_total;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String moeda;

    //Relacionamento com Usuário
    @ManyToOne
    @JsonIgnoreProperties("viagem")
    @JsonBackReference
    private ModelUsuario usuario;

    //Relacionamento com Destino
    @ManyToOne
    @JsonIgnoreProperties("viagem")
    private DestinoModel destino;

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

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDate data_fim) {
        this.data_fim = data_fim;
    }

    public String getEstilo_viagem() {
        return estilo_viagem;
    }

    public void setEstilo_viagem(String estilo_viagem) {
        this.estilo_viagem = estilo_viagem;
    }

    public String getCompanhia() {
        return companhia;
    }

    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCusto_total() {
        return custo_total;
    }

    public void setCusto_total(double custo_total) {
        this.custo_total = custo_total;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public ModelUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(ModelUsuario usuario) {
        this.usuario = usuario;
    }

    public DestinoModel getDestino() {
        return destino;
    }

    public void setDestino(DestinoModel destino) {
        this.destino = destino;
    }
}
