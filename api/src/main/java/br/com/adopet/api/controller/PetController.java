package br.com.adopet.api.controller;

import br.com.adopet.api.domain.service.PetService;
import br.com.adopet.api.dto.pet.DadosAtualizarPet;
import br.com.adopet.api.dto.pet.DadosCadastroPet;
import br.com.adopet.api.dto.pet.DadosListagemPet;
import br.com.adopet.api.dto.pet.PetDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PetDTO> criar(@RequestBody @Valid DadosCadastroPet dados) {
        return service.criar(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemPet>> buscarTodos() {
        return service.buscarTodos();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PetDTO> atualizar(@RequestBody DadosAtualizarPet dados, @PathVariable Long id) {
        return service.atualizar(dados, id);
    }

}
