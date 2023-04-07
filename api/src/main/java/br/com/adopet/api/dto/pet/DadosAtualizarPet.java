package br.com.adopet.api.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosAtualizarPet {

    private String nome;
    private String descricao;
    private String idade;
    private String imagem;

}
