package br.com.adopet.api.controller;

import br.com.adopet.api.domain.service.AdocaoService;
import br.com.adopet.api.dto.doacao.AdocaoDTO;
import br.com.adopet.api.dto.doacao.DadosRealizarAdocao;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adocao")
public class AdocaoController {

    private final AdocaoService service;

    public AdocaoController(AdocaoService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AdocaoDTO> adotar(@RequestBody DadosRealizarAdocao dados) {
        return service.adotar(dados);
    }

}
