package com.example.task5.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_UNIDADE")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_UNIDADE")
    @SequenceGenerator( name = "SQ_UNIDADE", sequenceName = "SQ_UNIDADE")
    @Column(name = "ID_UNIDADE")
    private Long unidadeId;

    @Column(name = "NOME_VALOR_RECEITA_UNIDADE")
    private String nomeMedidaReceita;

    @Column(name = "QUANTIDADE_PADRAO_UNIDADE")
    private Double quantidadePadrao;

    @Column(name = "NOME_VALOR_PADRAO_UNIDADE")
    private String nomeMedidaPadrao;

}
