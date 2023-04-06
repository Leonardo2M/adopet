package br.com.adopet.api.controller;

import br.com.adopet.api.domain.service.AdocaoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adocao")
public class AdocaoController {

    private final AdocaoService service;

    public AdocaoController(AdocaoService service) {
        this.service = service;
    }


}
