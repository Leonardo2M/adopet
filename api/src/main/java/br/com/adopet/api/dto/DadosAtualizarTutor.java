package br.com.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosAtualizarTutor {
    
    private String nome;

    @Pattern(regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}")
    private String telefone;

    private String cidade;

    private String sobre;

}
