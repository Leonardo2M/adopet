package br.com.adopet.api.domain.service;

import br.com.adopet.api.domain.repository.AdocaoRepository;
import br.com.adopet.api.domain.repository.PetRepository;
import br.com.adopet.api.domain.repository.TutorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AdocaoService {

    private final AdocaoRepository adocaoRepository;
    private final TutorRepository tutorRepository;
    private final PetRepository petRepository;
    private final ModelMapper modelMapper;

    public AdocaoService(AdocaoRepository adocaoRepository, TutorRepository tutorRepository, PetRepository petRepository, ModelMapper modelMapper) {
        this.adocaoRepository = adocaoRepository;
        this.tutorRepository = tutorRepository;
        this.petRepository = petRepository;
        this.modelMapper = modelMapper;
    }
}
