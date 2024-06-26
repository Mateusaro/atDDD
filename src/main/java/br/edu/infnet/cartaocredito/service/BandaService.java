package br.edu.infnet.cartaocredito.service;

import br.edu.infnet.cartaocredito.domain.Banda;
import br.edu.infnet.cartaocredito.repository.BandaRepository;
import br.edu.infnet.cartaocredito.service.dto.BandaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BandaService {

    private final BandaRepository bandaRepository;

    @Autowired
    public BandaService(BandaRepository bandaRepository) {
        this.bandaRepository = bandaRepository;
    }

    public List<Banda> listarBandas() {
        return bandaRepository.findAll();
    }

    public Banda obterBandaPorId(UUID id) {
        return bandaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banda não encontrada"));
    }

    public Banda criarBanda(BandaDto bandaDto) {
        Banda novaBanda = new Banda();
        novaBanda.setNome(bandaDto.getNome());
        novaBanda.setDescricao(bandaDto.getDescricao());
        return bandaRepository.save(novaBanda);
    }

    public Banda atualizarBanda(UUID id, BandaDto bandaDto) {
        Banda bandaExistente = obterBandaPorId(id);
        bandaExistente.setNome(bandaDto.getNome());
        bandaExistente.setDescricao(bandaDto.getDescricao());
        return bandaRepository.save(bandaExistente);
    }

    public void deletarBanda(UUID id) {
        Banda bandaParaDeletar = obterBandaPorId(id);
        bandaRepository.delete(bandaParaDeletar);
    }
}
