package br.edu.infnet.cartaocredito.controller;

import br.edu.infnet.cartaocredito.service.BandaService;
import br.edu.infnet.cartaocredito.service.dto.BandaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bandas")
public class BandaController {

    private final BandaService bandaService;

    @Autowired
    public BandaController(BandaService bandaService) {
        this.bandaService = bandaService;
    }

    @GetMapping
    public List<BandaDto> listarBandas() {
        return bandaService.listarBandas().stream()
                .map(BandaDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BandaDto obterBandaPorId(@PathVariable UUID id) {
        return new BandaDto(bandaService.obterBandaPorId(id));
    }

    @PostMapping
    public BandaDto criarBanda(@RequestBody BandaDto bandaDto) {
        return new BandaDto(bandaService.criarBanda(bandaDto));
    }

    @PutMapping("/{id}")
    public BandaDto atualizarBanda(@PathVariable UUID id, @RequestBody BandaDto bandaDto) {
        return new BandaDto(bandaService.atualizarBanda(id, bandaDto));
    }

    @DeleteMapping("/{id}")
    public void deletarBanda(@PathVariable UUID id) {
        bandaService.deletarBanda(id);
    }
}
