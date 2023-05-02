package br.com.adopet.api.domain.model;

import br.com.adopet.api.domain.model.usuario.Usuario;
import br.com.adopet.api.dto.tutor.DadosAtualizarTutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tutores")
public class Tutor  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String cidade;
    private String estado;
    private String sobre;
    @URL
    private String image;
    @OneToOne
    private Usuario user;

    public void atualizar(DadosAtualizarTutor dados) {
        if(dados.getNome() != null){
            this.nome = dados.getNome();
        }
        if(dados.getTelefone() != null){
            this.telefone = dados.getTelefone();
        }
        if(dados.getCidade() != null){
            this.cidade = dados.getCidade();
        }
        if(dados.getSobre() != null){
            this.sobre = dados.getSobre();
        }
    }
}
