package br.edu.infnet.cartaocredito.service;

import br.edu.infnet.cartaocredito.domain.Cartao;
import br.edu.infnet.cartaocredito.domain.Musica;
import br.edu.infnet.cartaocredito.domain.Plano;
import br.edu.infnet.cartaocredito.domain.Usuario;
import br.edu.infnet.cartaocredito.repository.MusicaRepository;
import br.edu.infnet.cartaocredito.repository.PlanoRepository;
import br.edu.infnet.cartaocredito.repository.UsuarioRepository;
import br.edu.infnet.cartaocredito.service.dto.AdicionarMusicaDto;
import br.edu.infnet.cartaocredito.service.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final MusicaRepository musicaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PlanoRepository planoRepository;

    @Autowired
    public UsuarioService(MusicaRepository musicaRepository, UsuarioRepository usuarioRepository, PlanoRepository planoRepository) {
        this.musicaRepository = musicaRepository;
        this.usuarioRepository = usuarioRepository;
        this.planoRepository = planoRepository;
    }

    @Transactional
    public UsuarioDto criar(UsuarioDto dto) throws Exception {
        // Verificar se temos um plano válido
        Optional<Plano> optionalPlano = planoRepository.findById(dto.getIdPlano());
        Plano plano = optionalPlano.orElseThrow(() -> new Exception("Plano não encontrado"));

        // Verificar se o CPF já está cadastrado
        if (usuarioRepository.findUsuarioByCpf(dto.getCpf()).isPresent()) {
            throw new Exception("CPF do usuário já cadastrado");
        }

        // Criar um objeto de cartão
        Cartao cartao = new Cartao();
        cartao.setNumero(dto.getNumeroCartao());
        cartao.setAtivo(dto.getCartaoAtivo());
        cartao.setLimite(dto.getLimite());

        // Criar o usuário
        Usuario usuario = new Usuario();
        usuario.criar(dto.getNome(), dto.getEmail(), dto.getSenha(), dto.getCpf(), cartao, plano);

        // Salvar o usuário
        usuario = usuarioRepository.save(usuario);

        // Cria a resposta para o controller
        UsuarioDto response = new UsuarioDto();
        response.setId(usuario.getId());
        response.setEmail(usuario.getEmail());
        response.setNome(usuario.getNome());
        response.setSenha("************");
        response.setNumeroCartao("***** ***** ***** *****");
        response.setCartaoAtivo(cartao.getAtivo());
        response.setLimite(cartao.getLimite());
        response.setIdPlano(plano.getId());
        response.setCpf(usuario.getCpf());

        return response;
    }

    @Transactional
    public void adicionarMusicaAPlaylist(UUID idUsuario, AdicionarMusicaDto dto) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));

        // Verifica se a música existe
        Musica musica = musicaRepository.findById(dto.getIdMusica())
                .orElseThrow(() -> new Exception("Música não encontrada"));

        // Favorita a música na playlist especificada
        usuario.favoritarMusica(dto.getIdPlaylist(), musica);

        // Salva as alterações no usuário
        usuarioRepository.save(usuario);
    }

    public Usuario obterUsuarioPorId(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
