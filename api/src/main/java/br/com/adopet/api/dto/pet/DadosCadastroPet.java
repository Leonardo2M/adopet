package br.com.adopet.api.dto.pet;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosCadastroPet {

    @NotBlank
    private String nome;
    @NotBlank
    private String descrição;
    @NotBlank
    private Integer idade;
    @NotBlank
    private String endereço;
    @NotBlank
    private String imagem;

}
