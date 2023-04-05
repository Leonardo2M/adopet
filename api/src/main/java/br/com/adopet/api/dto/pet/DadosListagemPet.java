package br.com.adopet.api.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosListagemPet {

    private String nome;
    private String idade;
    private String descricao;

}
