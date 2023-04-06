package br.com.adopet.api.dto.doacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdocaoDTO {

    private Long id;
    private Long idPet;
    private Long idTutor;
}
