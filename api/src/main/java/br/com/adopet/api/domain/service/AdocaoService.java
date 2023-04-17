package br.com.adopet.api.domain.service;

import br.com.adopet.api.domain.model.Adocao;
import br.com.adopet.api.domain.repository.AdocaoRepository;
import br.com.adopet.api.domain.repository.PetRepository;
import br.com.adopet.api.domain.repository.TutorRepository;
import br.com.adopet.api.domain.service.exception.AdopetException;
import br.com.adopet.api.dto.doacao.AdocaoDTO;
import br.com.adopet.api.dto.doacao.DadosRealizarAdocao;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

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

    public ResponseEntity<AdocaoDTO> adotar(DadosRealizarAdocao dados, UriComponentsBuilder uriComponentsBuilder) {
        var tutor = tutorRepository.findById(dados.getIdTutor()).orElseThrow(() -> new AdopetException("Não foi encontrado tutor com id = " + dados.getIdTutor()));
        var pet = petRepository.findById(dados.getIdPet()).orElseThrow(() -> new AdopetException("Não foi encontrado pet com id = " + dados.getIdPet()));

        if(pet.isAdoted()) {
            throw new AdopetException("Pet já foi adotado.");
        }

        var adocao = new Adocao(tutor, pet);
        adocaoRepository.save(adocao);
        pet.adotado();
        var uri = uriComponentsBuilder.path("/adocao/{id}").buildAndExpand(adocao.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(adocao, AdocaoDTO.class));
    }

    public ResponseEntity<?> deletar(Long id) {
        if(!adocaoRepository.existsById(id)) {
            throw new AdopetException("Não foi encontrado adoção com id = " + id);
        }

        adocaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
