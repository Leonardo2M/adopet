package br.com.adopet.api.controller;

import br.com.adopet.api.domain.service.AbrigoService;
import br.com.adopet.api.dto.abrigo.AbrigoDTO;
import br.com.adopet.api.dto.abrigo.DadosAtualizarAbrigo;
import br.com.adopet.api.dto.abrigo.DadosCadatroAbrigo;
import br.com.adopet.api.dto.abrigo.DadosListagemAbrigo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    private final AbrigoService service;

    public AbrigoController(AbrigoService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AbrigoDTO> criar(@RequestBody @Valid DadosCadatroAbrigo dados, UriComponentsBuilder uriComponentsBuilder) {
        return service.criar(dados, uriComponentsBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbrigoDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemAbrigo>> buscarTodos() {
        return service.buscarTodos();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AbrigoDTO> ataulizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizarAbrigo dados) {
        return service.atualizar(dados, id);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        return service.excluir(id);
    }

}
