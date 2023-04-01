package br.com.adopet.api.controller;

import br.com.adopet.api.domain.service.TutorService;
import br.com.adopet.api.dto.DadosCadastroTutor;
import br.com.adopet.api.dto.DadosListagemTutor;
import br.com.adopet.api.dto.TutorDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<TutorDTO> criarTutor(@RequestBody DadosCadastroTutor dados) {
        return service.criarTutor(dados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping()
    public ResponseEntity<List<DadosListagemTutor>> buscarPorId() {
        return service.buscarTodos();
    }
}
