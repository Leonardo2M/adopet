package br.com.adopet.api.domain.service;

import br.com.adopet.api.domain.model.Tutor;
import br.com.adopet.api.domain.repository.TutorRepository;
import br.com.adopet.api.dto.DadosCadastroTutor;
import br.com.adopet.api.dto.DadosListagemTutor;
import br.com.adopet.api.dto.TutorDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class TutorService {

    private final TutorRepository repository;
    private final ModelMapper modelMapper;

    public TutorService(TutorRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<TutorDTO> criarTutor(DadosCadastroTutor dados) {
        var tutor = modelMapper.map(dados, Tutor.class);
        repository.save(tutor);
        return ResponseEntity.ok().body(modelMapper.map(tutor, TutorDTO.class));
    }

    public ResponseEntity<TutorDTO> buscarPorId(Long id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        var tutor = repository.getReferenceById(id);
        return ResponseEntity.ok().body(modelMapper.map(tutor, TutorDTO.class));
    }

    public ResponseEntity<List<DadosListagemTutor>> buscarTodos() {
        var tutores = repository.findAll().stream().map(t -> modelMapper.map(t, DadosListagemTutor.class)).toList();

        if(tutores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(tutores);
    }
}
