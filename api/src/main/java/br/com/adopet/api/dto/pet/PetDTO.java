package br.com.adopet.api.dto.pet;

import br.com.adopet.api.domain.model.Abrigo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Boolean adotado = Boolean.FALSE;
    private String idade;
    private String imagem;

}
