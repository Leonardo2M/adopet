package br.com.adopet.api.domain.service;

import br.com.adopet.api.domain.model.Tutor;
import br.com.adopet.api.domain.repository.TutorRepository;
import br.com.adopet.api.domain.service.exception.AdopetException;
import br.com.adopet.api.dto.tutor.DadosAtualizarTutor;
import br.com.adopet.api.dto.tutor.DadosCadastroTutor;
import br.com.adopet.api.dto.tutor.DadosListagemTutor;
import br.com.adopet.api.dto.tutor.TutorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class TutorService {

    private final TutorRepository repository;
    private final ModelMapper modelMapper;

    public TutorService(TutorRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<TutorDTO> criarTutor(DadosCadastroTutor dados, UriComponentsBuilder uriComponentsBuilder) {
        var tutor = modelMapper.map(dados, Tutor.class);
        repository.save(tutor);
        var uri = uriComponentsBuilder.path("/tutor/{id}").buildAndExpand(tutor.getId()).toUri();
        return ResponseEntity.created(uri).body(modelMapper.map(tutor, TutorDTO.class));
    }

    public ResponseEntity<TutorDTO> buscarPorId(Long id) {
        var tutor = repository.findById(id).orElseThrow(() -> new AdopetException("Não existe tutor com id = " + id));
        return ResponseEntity.ok().body(modelMapper.map(tutor, TutorDTO.class));
    }

    public ResponseEntity<List<DadosListagemTutor>> buscarTodos() {
        var tutores = repository.findAll().stream().map(t -> modelMapper.map(t, DadosListagemTutor.class)).toList();

        if(tutores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(tutores);
    }

    public ResponseEntity<TutorDTO> atualizar(DadosAtualizarTutor dados, Long id) {
        var tutor = repository.findById(id).orElseThrow(() -> new AdopetException("Não existe tutor com id = " + id));
        tutor.atualizar(dados);
        return ResponseEntity.ok().body(modelMapper.map(tutor, TutorDTO.class));
    }

    public ResponseEntity<TutorDTO> excluir(Long id) {
        if(!repository.existsById(id)) {
            throw new AdopetException("Não existe tutor com id = " + id);
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }



}
