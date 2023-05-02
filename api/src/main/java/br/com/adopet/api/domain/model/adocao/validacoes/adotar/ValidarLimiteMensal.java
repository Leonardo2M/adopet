package br.com.adopet.api.domain.model.adocao.validacoes.adotar;

import br.com.adopet.api.domain.exception.AdopetException;
import br.com.adopet.api.domain.repository.AdocaoRepository;
import br.com.adopet.api.dto.doacao.DadosRealizarAdocao;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Component
public class ValidarLimiteMensal implements ValidacoesAdocao{

    private final AdocaoRepository adocaoRepository;

    public ValidarLimiteMensal(AdocaoRepository adocaoRepository) {
        this.adocaoRepository = adocaoRepository;
    }


    @Override
    public void validar(DadosRealizarAdocao dados) {
        var quantidadeMensal = adocaoRepository.countByTutorIdAndDataNow(dados.getIdTutor());

        if(quantidadeMensal > 2) {
            throw new AdopetException("Limite de adoção atingido no mês de " + LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault()));
        }
    }
}
