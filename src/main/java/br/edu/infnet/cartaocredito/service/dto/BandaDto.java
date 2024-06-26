package br.edu.infnet.cartaocredito.service.dto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.edu.infnet.cartaocredito.domain.Banda;
import br.edu.infnet.cartaocredito.service.dto.MusicaDto;
import lombok.Data;

@Data
public class BandaDto {
    private UUID id;
    private String nome;
    private String descricao;
    private List<MusicaDto> musicas;

    // Construtor padrão necessário para desserialização JSON
    public BandaDto() {
    }

    public BandaDto(Banda banda) {
        this.id = banda.getId();
        this.nome = banda.getNome();
        this.descricao = banda.getDescricao();
        this.musicas = banda.getMusicas().stream()
                .map(MusicaDto::new)
                .collect(Collectors.toList());
    }
}
