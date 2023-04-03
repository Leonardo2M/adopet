package br.com.adopet.api.dto.abrigo;

import br.com.adopet.api.domain.model.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbrigoDTO {

    private Long id;
    private String nome;
    private List<Pet> pets;

}
