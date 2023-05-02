package br.com.adopet.api.domain.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class AdocaoRepositoryTest {

    @Autowired
    private AdocaoRepository repository;

    @Test
    @DisplayName("Deveria devolver 0 já que não tem adocoes do tutor de id 1")
    void countByTutorIdAndDataNow_SemAdocaoNoMes() {
        var quantidade = repository.countByTutorIdAndDataNow(1L);

        Assertions.assertThat(quantidade).isEqualTo(0);
    }

    @Test
    @DisplayName("Deveria devolver 0 já as adoções não são do mês atual")
    void countByTutorIdAndDataNow_AdocaoEmOutroMes() {
        var quantidade = repository.countByTutorIdAndDataNow(3L);

        Assertions.assertThat(quantidade).isEqualTo(0);
    }

    @Test
    @DisplayName("Deveria devolver 3 adoções no mês atual")
    void countByTutorIdAndDataNow_TresAdocoes() {
        var quantidade = repository.countByTutorIdAndDataNow(4L);

        Assertions.assertThat(quantidade).isEqualTo(3);
    }
}