package br.com.adopet.api.dto.tutor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosListagemTutor {

    private String nome;
    private String telefone;
    private String cidade;
}
