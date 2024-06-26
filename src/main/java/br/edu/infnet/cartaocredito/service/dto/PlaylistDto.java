package br.edu.infnet.cartaocredito.service.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PlaylistDto {
    @NotEmpty(message = "Campo nome é obrigatório")
    private String nome;

    @NotEmpty(message = "Campo idUsuario é obrigatório")
    private String idUsuario; // Deve ser uma String, não um UUID diretamente

    private List<UUID> musicasIds;
}
