package br.com.adopet.api.domain.model;

import br.com.adopet.api.dto.abrigo.DadosAtualizarAbrigo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name = "abrigos")
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany
    private List<Pet> pets = new ArrayList<>();

    public void atualizar(DadosAtualizarAbrigo dados) {
        this.nome = dados.getNome();
    }
}
