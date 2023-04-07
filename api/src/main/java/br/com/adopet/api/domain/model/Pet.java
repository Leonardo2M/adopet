package br.com.adopet.api.domain.model;

import br.com.adopet.api.dto.pet.DadosAtualizarPet;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Boolean adotado = Boolean.FALSE;
    private String idade;
    private String imagem;
    @ManyToOne
    @JoinColumn(name = "abrigo_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Abrigo abrigo;

    public void atualizar(DadosAtualizarPet dados) {
        if(dados.getDescricao() != null){
            this.descricao = dados.getDescricao();
        }
        if(dados.getNome() != null){
            this.nome = dados.getNome();
        }
        if(dados.getImagem() != null){
            this.imagem = dados.getImagem();
        }
        if(dados.getIdade() != null) {
            this.idade = dados.getIdade();
        }
    }

    public void adotado() {
        adotado = Boolean.TRUE;
    }
}
