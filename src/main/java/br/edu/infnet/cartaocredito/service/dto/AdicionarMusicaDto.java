package br.edu.infnet.cartaocredito.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AdicionarMusicaDto {

    @NotNull(message = "ID da Playlist é obrigatório")
    private UUID idPlaylist;

    @NotNull(message = "ID da Música é obrigatório")
    private UUID idMusica;
}
