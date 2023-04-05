package br.com.adopet.api.domain.service;

import br.com.adopet.api.domain.model.Pet;
import br.com.adopet.api.domain.repository.AbrigoRepository;
import br.com.adopet.api.domain.repository.PetRepository;
import br.com.adopet.api.dto.pet.DadosCadastroPet;
import br.com.adopet.api.dto.pet.DadosListagemPet;
import br.com.adopet.api.dto.pet.PetDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository repository;
    private final ModelMapper modelMapper;
    private final AbrigoRepository abrigoRepository;

    public PetService(PetRepository repository, ModelMapper modelMapper, AbrigoRepository abrigoRepository) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.abrigoRepository = abrigoRepository;
    }

    public ResponseEntity<PetDTO> criar(DadosCadastroPet dados) {

        if(!abrigoRepository.existsById(dados.getAbrigoId())) {
            return ResponseEntity.notFound().build();
        }

        var abrigo = abrigoRepository.getReferenceById(dados.getAbrigoId());
        var pet = modelMapper.map(dados, Pet.class);
        abrigo.adicionarPet(pet);
        repository.save(pet);

        return ResponseEntity.ok().body(modelMapper.map(pet, PetDTO.class));
    }

    public ResponseEntity<PetDTO> buscarPorId(Long id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        var pet = repository.getReferenceById(id);

        return ResponseEntity.ok().body(modelMapper.map(pet, PetDTO.class));
    }

    public ResponseEntity<List<DadosListagemPet>> buscarTodos() {
        var pets = repository.findAll().stream().map(p -> modelMapper.map(p, DadosListagemPet.class)).toList();

        if(pets.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(pets);
    }

}
