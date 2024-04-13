package com.example.task5.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_RECEITA")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RECEITA")
    @SequenceGenerator( name = "SQ_RECEITA", sequenceName = "SQ_RECEITA")
    @Column(name = "ID_RECEITA")
    private Long receitaId;

    @Column(name = "NOME_RECEITA")
    private String nome;

    @Column(name = "DESCRICAO_RECEITA")
    private String descricao;

    @Column(name = "ESTA_NA_LISTA_RECEITA")
    private boolean estaNaListaCompras;

    @OneToMany(mappedBy = "receita")
    private Set<CardapioReceita> cardapioReceitaSet = new HashSet<>();

    @OneToMany(mappedBy = "receita")
    private Set<ReceitaIngrediente> receitaIngredienteSet = new HashSet<>();

}
