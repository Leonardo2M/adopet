package br.com.adopet.api.controller;

import br.com.adopet.api.domain.service.AbrigoService;
import br.com.adopet.api.dto.abrigo.AbrigoDTO;
import br.com.adopet.api.dto.abrigo.DadosCadatroAbrigo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{abrigos}")
public class AbrigoController {

    private final AbrigoService service;

    public AbrigoController(AbrigoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AbrigoDTO> criar(@RequestBody DadosCadatroAbrigo dados) {
        return service.criar(dados);
    }
}
