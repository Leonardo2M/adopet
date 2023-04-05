package br.com.adopet.api.controller;

import br.com.adopet.api.domain.service.PetService;
import br.com.adopet.api.dto.pet.DadosCadastroPet;
import br.com.adopet.api.dto.pet.PetDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PetDTO> criar(@RequestBody DadosCadastroPet dados) {
        return service.criar(dados);
    }

}
