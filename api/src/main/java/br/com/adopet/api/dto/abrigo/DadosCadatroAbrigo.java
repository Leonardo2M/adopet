package br.com.adopet.api.dto.abrigo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosCadatroAbrigo {

    @NotBlank
    private String nome;
    @NotBlank
    private String localizacao;

}
