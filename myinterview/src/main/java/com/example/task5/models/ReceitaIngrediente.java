package com.example.task5.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_RECEITA_INGREDIENTE")
public class ReceitaIngrediente {

    @EmbeddedId
    private ReceitaIngredienteId receitaIngredienteId = new ReceitaIngredienteId();

    @ManyToOne
    @MapsId("receitaId")
    private Receita receita;

    @ManyToOne
    @MapsId("ingredienteId")
    private Ingrediente ingrediente;

    @ManyToOne
    private Unidade unidade;

    @Column(name = "QUANTIDADE_INGREDIENTE")
    private Double quantidade;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class ReceitaIngredienteId implements Serializable{

        private static final Long serialVersionUID = 1L;

        private Long receitaId;

        private Long ingredienteId;

    }

}
