package br.com.adopet.api.domain.service;

import br.com.adopet.api.domain.model.Abrigo;
import br.com.adopet.api.domain.repository.AbrigoRepository;
import br.com.adopet.api.dto.abrigo.AbrigoDTO;
import br.com.adopet.api.dto.abrigo.DadosAtualizarAbrigo;
import br.com.adopet.api.dto.abrigo.DadosCadatroAbrigo;
import br.com.adopet.api.dto.abrigo.DadosListagemAbrigo;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    private final AbrigoRepository repository;
    private final ModelMapper modelMapper;

    public AbrigoService(AbrigoRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<AbrigoDTO> criar(DadosCadatroAbrigo dados) {
        var abrigo = modelMapper.map(dados, Abrigo.class);
        repository.save(abrigo);

        return ResponseEntity.ok().body(modelMapper.map(abrigo, AbrigoDTO.class));
    }

    public ResponseEntity<AbrigoDTO> buscarPorId(Long id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        var abrigo = repository.getReferenceById(id);
        return ResponseEntity.ok().body(modelMapper.map(abrigo, AbrigoDTO.class));
    }

    public ResponseEntity<List<DadosListagemAbrigo>> buscarTodos() {
        var abrigos = repository.findAll().stream().map(a -> modelMapper.map(a, DadosListagemAbrigo.class)).toList();

        if(abrigos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(abrigos);
    }

    public ResponseEntity<AbrigoDTO> atualizar(DadosAtualizarAbrigo dados, Long id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        var abrigo = repository.getReferenceById(id);
        abrigo.atualizar(dados);

        return ResponseEntity.ok().body(modelMapper.map(abrigo, AbrigoDTO.class));
    }
}
