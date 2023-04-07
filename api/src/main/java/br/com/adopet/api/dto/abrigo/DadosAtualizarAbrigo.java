package br.com.adopet.api.dto.abrigo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosAtualizarAbrigo {

    private String nome;
    private String localizacao;

}
