package br.com.adopet.api.domain.service;

import br.com.adopet.api.domain.repository.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }
}
