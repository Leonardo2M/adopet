package br.com.adopet.api.controller;

import br.com.adopet.api.domain.service.AbrigoService;
import br.com.adopet.api.dto.abrigo.AbrigoDTO;
import br.com.adopet.api.dto.abrigo.DadosCadatroAbrigo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    private final AbrigoService service;

    public AbrigoController(AbrigoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AbrigoDTO> criar(@RequestBody DadosCadatroAbrigo dados) {
        return service.criar(dados);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AbrigoDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}
