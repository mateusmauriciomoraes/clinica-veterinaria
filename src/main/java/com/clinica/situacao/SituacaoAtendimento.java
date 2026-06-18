package com.clinica.situacao;

import com.clinica.domain.Atendimento;

public interface SituacaoAtendimento {

    void iniciar(Atendimento atendimento);

    void finalizar(Atendimento atendimento);

    void cancelar(Atendimento atendimento);

    String getNome();
}
