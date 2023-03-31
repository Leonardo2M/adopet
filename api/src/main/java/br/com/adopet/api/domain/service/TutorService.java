package br.com.adopet.api.domain.service;

import br.com.adopet.api.domain.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    private final TutorRepository repository;

    public TutorService(TutorRepository repository) {
        this.repository = repository;
    }

}
