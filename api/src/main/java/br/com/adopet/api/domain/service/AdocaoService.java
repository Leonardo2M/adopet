package br.com.adopet.api.domain.service;

import br.com.adopet.api.domain.model.adocao.Adocao;
import br.com.adopet.api.domain.model.adocao.validacoes.adotar.ValidacoesAdocao;
import br.com.adopet.api.domain.repository.AdocaoRepository;
import br.com.adopet.api.domain.repository.PetRepository;
import br.com.adopet.api.domain.repository.TutorRepository;
import br.com.adopet.api.domain.exception.AdopetException;
import br.com.adopet.api.dto.doacao.AdocaoDTO;
import br.com.adopet.api.dto.doacao.DadosRealizarAdocao;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class AdocaoService {

    private final AdocaoRepository adocaoRepository;
    private final TutorRepository tutorRepository;
    private final PetRepository petRepository;
    private final ModelMapper modelMapper;
    private List<ValidacoesAdocao> validacoes;

    public AdocaoService(AdocaoRepository adocaoRepository, TutorRepository tutorRepository, PetRepository petRepository, ModelMapper modelMapper, List<ValidacoesAdocao> validacoes) {
        this.adocaoRepository = adocaoRepository;
        this.tutorRepository = tutorRepository;
        this.petRepository = petRepository;
        this.modelMapper = modelMapper;
        this.validacoes = validacoes;
    }

    public ResponseEntity<AdocaoDTO> adotar(DadosRealizarAdocao dados, UriComponentsBuilder uriComponentsBuilder) {
        var tutor = tutorRepository.findById(dados.getIdTutor()).orElseThrow(() -> new AdopetException("Não foi encontrado tutor com id = " + dados.getIdTutor()));
        var pet = petRepository.findById(dados.getIdPet()).orElseThrow(() -> new AdopetException("Não foi encontrado pet com id = " + dados.getIdPet()));

        if(pet.isAdoted()) {
            throw new AdopetException("Pet já foi adotado.");
        }

        validacoes.forEach(v -> v.validar(dados));

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
