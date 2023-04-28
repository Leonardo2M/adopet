package br.com.adopet.api.domain.model.adocao.validacoes.adotar;

import br.com.adopet.api.dto.doacao.DadosRealizarAdocao;

public interface ValidacoesAdocao {

    void validar(DadosRealizarAdocao dados);

}
