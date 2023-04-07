package br.com.adopet.api.domain.model;

import br.com.adopet.api.dto.abrigo.DadosAtualizarAbrigo;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    private String localizacao;

    @OneToMany(mappedBy = "abrigo")
    private List<Pet> pets = new ArrayList<>();


    public void atualizar(DadosAtualizarAbrigo dados) {
        if(dados.getNome() != null) {
            this.nome = dados.getNome();
        }
        if(dados.getLocalizacao() != null) {
            this.localizacao = dados.getLocalizacao();;
        }
    }

    public void adicionarPet(Pet pet) {
        pets.add(pet);
        pet.setAbrigo(this);
    }
}
