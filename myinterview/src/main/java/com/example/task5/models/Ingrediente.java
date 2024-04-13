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
@Table(name = "TB_INGREDIENTE")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_INGREDIENTE")
    @SequenceGenerator( name = "SQ_INGREDIENTE", sequenceName = "SQ_INGREDIENTE")
    @Column(name = "ID_INGREDIENTE")
    private Long ingredienteId;

    @Column(name = "NOME_INGREDIENTE")
    private String nome;

    @OneToMany(mappedBy = "ingrediente")
    private Set<ReceitaIngrediente> receitaIngredienteSet = new HashSet<>();

}
