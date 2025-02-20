package br.edu.infnet.cartaocredito.repository;

import br.edu.infnet.cartaocredito.domain.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, UUID> {

    // Aqui você pode adicionar métodos personalizados, se necessário

}
