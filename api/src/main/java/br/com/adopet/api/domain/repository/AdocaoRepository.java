package br.com.adopet.api.domain.repository;

import br.com.adopet.api.domain.model.adocao.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, Long> {


    @Query("""
            SELECT COUNT(*) FROM Adocao
            WHERE tutor.id = :idTutor AND MONTH(data) = MONTH(now())
    """)
    int countByTutorIdAndDataNow(Long idTutor);

}
