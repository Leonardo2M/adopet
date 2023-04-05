package br.com.adopet.api.dto.pet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosCadastroPet {

    @NotBlank
    private String nome;
    @NotNull
    private Long abrigoId;
    @NotBlank
    private String descricao;
    @NotBlank
    private String idade;
    @NotBlank
    private String imagem;

}
