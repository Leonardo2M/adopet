package br.com.adopet.api.domain.service;

import br.com.adopet.api.domain.model.Pet;
import br.com.adopet.api.domain.repository.AbrigoRepository;
import br.com.adopet.api.domain.repository.PetRepository;
import br.com.adopet.api.domain.exception.AdopetException;
import br.com.adopet.api.dto.pet.DadosAtualizarPet;
import br.com.adopet.api.dto.pet.DadosCadastroPet;
import br.com.adopet.api.dto.pet.DadosListagemPet;
import br.com.adopet.api.dto.pet.PetDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

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

    public ResponseEntity<PetDTO> criar(DadosCadastroPet dados, UriComponentsBuilder uriComponentsBuilder) {
        var abrigo = abrigoRepository.findById(dados.getAbrigo().getId()).orElseThrow(() -> new AdopetException("Não existe abrigo com id = " + dados.getAbrigo().getId()));
        var pet = modelMapper.map(dados, Pet.class);
        abrigo.adicionarPet(pet);
        repository.save(pet);
        var uri = uriComponentsBuilder.path("/pets/{id}").buildAndExpand(pet.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(pet, PetDTO.class));
    }

    public ResponseEntity<PetDTO> buscarPorId(Long id) {
        var pet = repository.findById(id).orElseThrow(() -> new AdopetException("Não foi encontrado pet com id = " + id));
        return ResponseEntity.ok().body(modelMapper.map(pet, PetDTO.class));
    }

    public ResponseEntity<List<DadosListagemPet>> buscarTodos() {
        var pets = repository.findAll().stream().map(p -> modelMapper.map(p, DadosListagemPet.class)).toList();

        if(pets.isEmpty()) {
           throw new AdopetException("Não foi localizado nenhum pet");
        }

        return ResponseEntity.ok().body(pets);
    }

    public ResponseEntity<PetDTO> atualizar(DadosAtualizarPet dados, Long id) {
        var pet = repository.findById(id).orElseThrow(() -> new AdopetException("Não foi encontrado pet com id = " + id));
        pet.atualizar(dados);

        return ResponseEntity.ok().body(modelMapper.map(pet, PetDTO.class));
    }

    public ResponseEntity<?> excluir(Long id) {
        if(!repository.existsById(id)) {
            throw  new AdopetException("Não foi encontrado pet com id = " + id);
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
