package com.example.task5.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CARDAPIO_RECEITA")
public class CardapioReceita {

    @EmbeddedId
    private CardapioReceitaId cardapioReceitaId = new CardapioReceitaId();

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("cardapioId")
    private Cardapio cardapio;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("receitaId")
    private Receita receita;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "TIPO_REFEICAO",
            referencedColumnName = "ID_TIPO_REFEICAO",
            foreignKey = @ForeignKey(name = "FK_TB_CARDAPIO_RECEITA__TIPO_REFEICAO")
    )
    private TipoRefeicao tipoRefeicao;

    @Column(name = "DIA_CARDAPIO_RECEITA")
    private LocalDate dia;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class CardapioReceitaId implements Serializable{

        private static final Long serialVersionUID = 1L;

        private Long cardapioId;

        private Long receitaId;

    }

}
