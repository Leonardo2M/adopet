package br.com.adopet.api.dto.abrigo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosListagemAbrigo {

    private Long id;
    private String nome;

    private String localizacao;

}
