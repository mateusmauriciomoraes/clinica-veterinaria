package com.clinica.situacao;

import com.clinica.domain.Atendimento;

public class Finalizado implements SituacaoAtendimento {

    @Override
    public void iniciar(Atendimento atendimento) {
        throw new TransicaoInvalidaException("Atendimento finalizado não pode ser reiniciado.");
    }

    @Override
    public void finalizar(Atendimento atendimento) {
        throw new TransicaoInvalidaException("Atendimento já está finalizado.");
    }

    @Override
    public void cancelar(Atendimento atendimento) {
        throw new TransicaoInvalidaException("Atendimento finalizado não pode ser cancelado.");
    }

    @Override
    public String getNome() {
        return "Finalizado";
    }
}
