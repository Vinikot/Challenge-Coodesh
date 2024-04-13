package com.example.task5.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_TIPO_REFEICAO")
public class TipoRefeicao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TIPO_REFEICAO")
    @SequenceGenerator( name = "SQ_TIPO_REFEICAO", sequenceName = "SQ_TIPO_REFEICAO")
    @Column(name = "ID_TIPO_REFEICAO")
    private Long tipoRefeicaoId;

    @Column(name = "NOME_TIPO_REFEICAO")
    private String nome;

}
