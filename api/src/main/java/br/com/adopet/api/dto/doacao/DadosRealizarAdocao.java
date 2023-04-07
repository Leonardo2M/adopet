package br.com.adopet.api.dto.doacao;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosRealizarAdocao {

    @NotNull
    private Long idPet;
    @NotNull
    private Long idTutor;

}
