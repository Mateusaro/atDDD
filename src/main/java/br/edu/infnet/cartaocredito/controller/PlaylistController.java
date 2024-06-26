package br.edu.infnet.cartaocredito.controller;

import br.edu.infnet.cartaocredito.domain.Playlist;
import br.edu.infnet.cartaocredito.domain.Usuario;
import br.edu.infnet.cartaocredito.service.PlaylistService;
import br.edu.infnet.cartaocredito.service.UsuarioService;
import br.edu.infnet.cartaocredito.service.dto.PlaylistDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;
    private final UsuarioService usuarioService; // Importe o serviço de usuário se ainda não estiver importado


    @Autowired
    public PlaylistController(PlaylistService playlistService, UsuarioService usuarioService) {
        this.playlistService = playlistService;
        this.usuarioService = usuarioService;
    }


    @PostMapping
    public ResponseEntity<Playlist> criarPlaylist(@RequestBody @Valid PlaylistDto playlistDto) {
        // Obter o usuário pelo ID fornecido no DTO da playlist
        Usuario usuario = usuarioService.obterUsuarioPorId(UUID.fromString(playlistDto.getIdUsuario()));

        // Verificar se o usuário foi encontrado
        if (usuario == null) {
            throw new RuntimeException("Usuário não encontrado com ID: " + playlistDto.getIdUsuario());
        }

        // Criar a playlist e associar ao usuário
        Playlist playlist = new Playlist();
        playlist.setNome(playlistDto.getNome());
        playlist.setUsuario(usuario);

        // Aqui você pode adicionar lógica para associar músicas à playlist, se necessário

        // Salvar a playlist no serviço
        playlist = playlistService.criarPlaylist(playlist);

        return new ResponseEntity<>(playlist, HttpStatus.CREATED);
    }

    @PostMapping("/{playlistId}/musicas/{musicaId}")
    public ResponseEntity<Void> adicionarMusicaAPlaylist(@PathVariable UUID playlistId, @PathVariable UUID musicaId) {
        playlistService.adicionarMusicaAPlaylist(playlistId, musicaId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
