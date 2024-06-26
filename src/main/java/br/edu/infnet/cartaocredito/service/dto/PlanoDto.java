package br.edu.infnet.cartaocredito.service.dto;

import lombok.Data;

@Data
public class PlanoDto {
    private String nome;
    private boolean ativo;
    private String descricao;
    private double valor;
}
