package br.edu.infnet.cartaocredito.controller;

import br.edu.infnet.cartaocredito.service.UsuarioService;
import br.edu.infnet.cartaocredito.service.dto.AdicionarMusicaDto;
import br.edu.infnet.cartaocredito.service.dto.UsuarioDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<UsuarioDto> criar(@RequestBody @Valid UsuarioDto dto) throws Exception {
        UsuarioDto response = this.usuarioService.criar(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/{id}/playlist/musica")
    public ResponseEntity<Void> adicionarMusicaAPlaylist(@PathVariable UUID id, @RequestBody @Valid AdicionarMusicaDto dto) throws Exception {
        usuarioService.adicionarMusicaAPlaylist(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
