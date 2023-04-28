package br.com.adopet.api.domain.repository;

import br.com.adopet.api.domain.model.adocao.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
}
