package br.com.adopet.api.domain.service;

import br.com.adopet.api.domain.repository.abrigo.AbrigoRepository;
import org.springframework.stereotype.Service;

@Service
public class AbrigosService {

    private final AbrigoRepository repository;

    public AbrigosService(AbrigoRepository repository) {
        this.repository = repository;
    }

}
