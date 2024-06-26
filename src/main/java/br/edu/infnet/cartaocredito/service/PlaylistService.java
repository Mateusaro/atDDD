package br.edu.infnet.cartaocredito.service;

import br.edu.infnet.cartaocredito.domain.Musica;
import br.edu.infnet.cartaocredito.domain.Playlist;
import br.edu.infnet.cartaocredito.repository.PlaylistRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final MusicaService musicaService; // Supondo que exista um serviço para músicas

    @Transactional
    public Playlist criarPlaylist(Playlist playlistDto) {
        Playlist playlist = new Playlist();
        playlist.setNome(playlistDto.getNome());

        // Aqui você pode adicionar lógica para associar a playlist ao usuário com o idUsuario recebido

        return playlistRepository.save(playlist);
    }

    @Transactional
    public void adicionarMusicaAPlaylist(UUID playlistId, UUID musicaId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new RuntimeException("Playlist não encontrada com ID: " + playlistId));

        Musica musica = musicaService.obterMusicaPorId(musicaId); // Supondo que existe um método para buscar música por ID

        playlist.getMusicas().add(musica);

        playlistRepository.save(playlist);
    }
}
