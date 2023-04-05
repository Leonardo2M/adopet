package br.com.adopet.api.controller;

import br.com.adopet.api.domain.service.PetService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService service;
    private final ModelMapper modelMapper;

    public PetController(PetService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }
}
