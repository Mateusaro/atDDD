package br.edu.infnet.cartaocredito.controller;

import br.edu.infnet.cartaocredito.domain.Plano;
import br.edu.infnet.cartaocredito.service.PlanoService;
import br.edu.infnet.cartaocredito.service.dto.PlanoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/planos")
public class PlanoController {

    private final PlanoService planoService;

    @Autowired
    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @GetMapping
    public List<Plano> listarPlanos() {
        return planoService.listarPlanos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plano> obterPlanoPorId(@PathVariable UUID id) {
        Plano plano = planoService.obterPlanoPorId(id);
        return ResponseEntity.ok(plano);
    }

    @PostMapping
    public ResponseEntity<Plano> criarPlano(@RequestBody PlanoDto planoDto) {
        Plano novoPlano = planoService.criarPlano(planoDto);
        return ResponseEntity.ok(novoPlano);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plano> atualizarPlano(@PathVariable UUID id, @RequestBody PlanoDto planoDto) {
        Plano planoAtualizado = planoService.atualizarPlano(id, planoDto);
        return ResponseEntity.ok(planoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlano(@PathVariable UUID id) {
        planoService.deletarPlano(id);
        return ResponseEntity.noContent().build();
    }
}
