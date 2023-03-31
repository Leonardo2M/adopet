package br.com.adopet.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosCadastroTutor {

    private String nome;
    private String telefone;
    private String cidade;
    private String sobre;

}
