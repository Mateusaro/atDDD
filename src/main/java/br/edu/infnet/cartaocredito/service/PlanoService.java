package br.edu.infnet.cartaocredito.service;

import br.edu.infnet.cartaocredito.domain.Plano;
import br.edu.infnet.cartaocredito.repository.PlanoRepository;
import br.edu.infnet.cartaocredito.service.dto.PlanoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;

    @Autowired
    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public List<Plano> listarPlanos() {
        return planoRepository.findAll();
    }

    public Plano obterPlanoPorId(UUID id) {
        return planoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano não encontrado"));
    }

    public Plano criarPlano(PlanoDto planoDto) {
        Plano novoPlano = new Plano();
        novoPlano.setNome(planoDto.getNome());
        novoPlano.setAtivo(planoDto.isAtivo());
        novoPlano.setDescricao(planoDto.getDescricao());
        novoPlano.setValor(planoDto.getValor());
        return planoRepository.save(novoPlano);
    }

    public Plano atualizarPlano(UUID id, PlanoDto planoDto) {
        Plano planoExistente = obterPlanoPorId(id);
        planoExistente.setNome(planoDto.getNome());
        planoExistente.setAtivo(planoDto.isAtivo());
        planoExistente.setDescricao(planoDto.getDescricao());
        planoExistente.setValor(planoDto.getValor());
        return planoRepository.save(planoExistente);
    }

    public void deletarPlano(UUID id) {
        Plano planoParaDeletar = obterPlanoPorId(id);
        planoRepository.delete(planoParaDeletar);
    }
}
