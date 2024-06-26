package br.edu.infnet.cartaocredito.controller;

import br.edu.infnet.cartaocredito.service.MusicaService;
import br.edu.infnet.cartaocredito.service.dto.MusicaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private final MusicaService musicaService;

    @Autowired
    public MusicaController(MusicaService musicaService) {
        this.musicaService = musicaService;
    }

    @GetMapping
    public List<MusicaDto> listarMusicas() {
        return musicaService.listarMusicas().stream()
                .map(MusicaDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MusicaDto obterMusicaPorId(@PathVariable UUID id) {
        return new MusicaDto(musicaService.obterMusicaPorId(id));
    }

    @PostMapping
    public MusicaDto criarMusica(@RequestBody MusicaDto musicaDto) {
        return new MusicaDto(musicaService.criarMusica(musicaDto));
    }

    @PutMapping("/{id}")
    public MusicaDto atualizarMusica(@PathVariable UUID id, @RequestBody MusicaDto musicaDto) {
        return new MusicaDto(musicaService.atualizarMusica(id, musicaDto));
    }

    @DeleteMapping("/{id}")
    public void deletarMusica(@PathVariable UUID id) {
        musicaService.deletarMusica(id);
    }
}
