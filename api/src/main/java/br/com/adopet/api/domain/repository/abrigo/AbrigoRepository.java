package br.com.adopet.api.domain.repository.abrigo;

import br.com.adopet.api.domain.model.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {
}
