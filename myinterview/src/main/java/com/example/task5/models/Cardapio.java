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
@Table(name = "TB_CARDAPIO")
public class Cardapio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CARDAPIO")
    @SequenceGenerator( name = "SQ_CARDAPIO", sequenceName = "SQ_CARDAPIO")
    @Column(name = "ID_CARDAPIO")
    private Long cardapioId;

    @Column(name = "NOME_CARDAPIO")
    private String nome;

    @OneToMany(mappedBy = "cardapio")
    private Set<CardapioReceita> cardapioReceitaSet = new HashSet<>();

}
