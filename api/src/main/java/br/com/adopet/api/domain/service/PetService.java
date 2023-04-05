package br.com.adopet.api.domain.service;

import br.com.adopet.api.domain.model.Pet;
import br.com.adopet.api.domain.repository.PetRepository;
import br.com.adopet.api.dto.pet.DadosCadastroPet;
import br.com.adopet.api.dto.pet.PetDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetRepository repository;
    private final ModelMapper modelMapper;

    public PetService(PetRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<PetDTO> criar(DadosCadastroPet dados) {
        var pet = modelMapper.map(dados, Pet.class);
        repository.save(pet);

        return ResponseEntity.ok().body(modelMapper.map(pet, PetDTO.class));
    }
}
