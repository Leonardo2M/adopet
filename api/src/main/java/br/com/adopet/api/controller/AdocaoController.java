package br.com.adopet.api.controller;

import br.com.adopet.api.domain.service.AdocaoService;
import br.com.adopet.api.dto.doacao.AdocaoDTO;
import br.com.adopet.api.dto.doacao.DadosRealizarAdocao;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/adocao")
public class AdocaoController {

    private final AdocaoService service;

    public AdocaoController(AdocaoService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AdocaoDTO> adotar(@RequestBody DadosRealizarAdocao dados, UriComponentsBuilder uriComponentsBuilder) {
        return service.adotar(dados, uriComponentsBuilder);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return service.deletar(id);
    }

}
