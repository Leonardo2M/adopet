package br.com.adopet.api.dto.tutor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosCadastroTutor {

    @NotBlank
    private String nome;
    @NotBlank
    @Pattern(regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}")
    private String telefone;
    @NotBlank
    private String cidade;
    @NotBlank
    private String sobre;

}
