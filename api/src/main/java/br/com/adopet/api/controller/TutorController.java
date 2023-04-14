package br.com.adopet.api.controller;

import br.com.adopet.api.domain.service.TutorService;
import br.com.adopet.api.dto.tutor.DadosAtualizarTutor;
import br.com.adopet.api.dto.tutor.DadosCadastroTutor;
import br.com.adopet.api.dto.tutor.DadosListagemTutor;
import br.com.adopet.api.dto.tutor.TutorDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    private final TutorService service;

    public TutorController(TutorService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TutorDTO> criarTutor(@RequestBody @Valid DadosCadastroTutor dados, UriComponentsBuilder uriComponentsBuilder) {
        return service.criarTutor(dados, uriComponentsBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping()
    public ResponseEntity<List<DadosListagemTutor>> buscarPorId() {
        return service.buscarTodos();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TutorDTO> atualizar(@RequestBody @Valid DadosAtualizarTutor dados, @PathVariable Long id) {
        return service.atualizar(dados, id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        return service.excluir(id);
    }
}
