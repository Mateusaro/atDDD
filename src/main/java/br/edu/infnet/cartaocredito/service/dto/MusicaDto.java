package br.edu.infnet.cartaocredito.service.dto;

import br.edu.infnet.cartaocredito.domain.Musica;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class MusicaDto {
    // Getters e setters
    private UUID id;
    private String nome;
    private int duracao;
    private UUID bandaId;

    // Construtor padrão vazio é necessário para a desserialização JSON
    public MusicaDto() {
    }

    // Construtor que recebe uma instância de Musica para conversão
    public MusicaDto(Musica musica) {
        this.id = musica.getId();
        this.nome = musica.getNome();
        this.duracao = musica.getDuracao();
        if (musica.getBanda() != null) {
            this.bandaId = musica.getBanda().getId();
        }
    }

}
