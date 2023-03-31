package br.com.adopet.api.domain.service;

import br.com.adopet.api.domain.repository.TutorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    private final TutorRepository repository;
    private final ModelMapper modelMapper;

    public TutorService(TutorRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

}
