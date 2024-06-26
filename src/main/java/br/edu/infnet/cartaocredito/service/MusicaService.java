package br.edu.infnet.cartaocredito.service;

import br.edu.infnet.cartaocredito.domain.Banda;
import br.edu.infnet.cartaocredito.domain.Musica;
import br.edu.infnet.cartaocredito.repository.BandaRepository;
import br.edu.infnet.cartaocredito.repository.MusicaRepository;
import br.edu.infnet.cartaocredito.service.dto.MusicaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MusicaService {

    private final MusicaRepository musicaRepository;
    private final BandaRepository bandaRepository;

    @Autowired
    public MusicaService(MusicaRepository musicaRepository, BandaRepository bandaRepository) {
        this.musicaRepository = musicaRepository;
        this.bandaRepository = bandaRepository;
    }

    public List<Musica> listarMusicas() {
        return musicaRepository.findAll();
    }

    public Musica obterMusicaPorId(UUID id) {
        return musicaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Música não encontrada"));
    }

    public Musica criarMusica(MusicaDto musicaDto) {
        Banda banda = bandaRepository.findById(musicaDto.getBandaId())
                .orElseThrow(() -> new RuntimeException("Banda não encontrada"));

        Musica novaMusica = new Musica();
        novaMusica.setNome(musicaDto.getNome());
        novaMusica.setDuracao(musicaDto.getDuracao());
        // Alocando a banda na música
        novaMusica.setBanda(banda);

        return musicaRepository.save(novaMusica);
    }

    public Musica atualizarMusica(UUID id, MusicaDto musicaDto) {
        Musica musicaExistente = obterMusicaPorId(id);
        musicaExistente.setNome(musicaDto.getNome());
        musicaExistente.setDuracao(musicaDto.getDuracao());
        // Alocando a banda na música (se for o caso de atualização da banda)
        if (musicaDto.getBandaId() != null) {
            Banda banda = bandaRepository.findById(musicaDto.getBandaId())
                    .orElseThrow(() -> new RuntimeException("Banda não encontrada"));
            musicaExistente.setBanda(banda);
        }

        return musicaRepository.save(musicaExistente);
    }

    public void deletarMusica(UUID id) {
        Musica musicaParaDeletar = obterMusicaPorId(id);
        musicaRepository.delete(musicaParaDeletar);
    }
}
