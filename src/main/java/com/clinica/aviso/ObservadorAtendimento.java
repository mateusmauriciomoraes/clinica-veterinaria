package com.clinica.aviso;

import com.clinica.domain.Atendimento;
import com.clinica.situacao.SituacaoAtendimento;

public interface ObservadorAtendimento {

    void atualizar(Atendimento atendimento, SituacaoAtendimento situacaoAnterior, SituacaoAtendimento situacaoAtual);

    boolean interessa(SituacaoAtendimento situacaoAnterior, SituacaoAtendimento situacaoAtual);
}
