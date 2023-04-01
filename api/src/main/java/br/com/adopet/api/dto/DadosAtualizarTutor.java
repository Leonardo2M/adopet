package br.com.adopet.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosAtualizarTutor {

    private String nome;
    private String telefone;
    private String cidade;
    private String sobre;

}
