package br.com.adopet.api.dto.doacao;

import br.com.adopet.api.domain.model.Pet;
import br.com.adopet.api.domain.model.Tutor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdocaoDTO {

    private Long id;
    private Pet idPet;
    private Tutor idTutor;
}
