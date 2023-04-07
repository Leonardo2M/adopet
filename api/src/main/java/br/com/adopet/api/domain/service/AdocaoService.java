package br.com.adopet.api.domain.service;

import br.com.adopet.api.domain.model.Adocao;
import br.com.adopet.api.domain.repository.AdocaoRepository;
import br.com.adopet.api.domain.repository.PetRepository;
import br.com.adopet.api.domain.repository.TutorRepository;
import br.com.adopet.api.dto.doacao.AdocaoDTO;
import br.com.adopet.api.dto.doacao.DadosRealizarAdocao;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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

    public ResponseEntity<AdocaoDTO> adotar(DadosRealizarAdocao dados) {
        var tutor = tutorRepository.findById(dados.getIdTutor()).orElseThrow(NoSuchElementException::new);
        var pet = petRepository.findById(dados.getIdPet()).orElseThrow(NoSuchElementException::new);

        var adocao = new Adocao(tutor, pet);
        adocaoRepository.save(adocao);

        return ResponseEntity.ok().body(modelMapper.map(adocao, AdocaoDTO.class));
    }

}