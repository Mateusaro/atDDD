package br.edu.infnet.cartaocredito.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Banda {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String nome;
    @Column
    private String descricao;

    // Getters e setters
    @Setter
    @Getter
    @OneToMany(mappedBy = "banda", cascade = CascadeType.ALL)
    private List<Musica> musicas = new ArrayList<>();

    // Construtor
    public Banda() {
        // Inicializa a lista de músicas vazia
        this.musicas = new ArrayList<>();
    }

}
