package br.edu.infnet.cartaocredito.repository;

import br.edu.infnet.cartaocredito.domain.Banda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BandaRepository extends JpaRepository<Banda, UUID> {

}
