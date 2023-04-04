package br.com.adopet.api.controller;

import br.com.adopet.api.domain.service.AbrigoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/{abrigos}")
public class AbrigoController {

    private final AbrigoService service;

    public AbrigoController(AbrigoService service) {
        this.service = service;
    }


}
