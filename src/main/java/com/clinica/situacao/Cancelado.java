package com.clinica.situacao;

import com.clinica.domain.Atendimento;

public class Cancelado implements SituacaoAtendimento {

    @Override
    public void iniciar(Atendimento atendimento) {
        throw new TransicaoInvalidaException("Atendimento cancelado não pode ser iniciado.");
    }

    @Override
    public void finalizar(Atendimento atendimento) {
        throw new TransicaoInvalidaException("Atendimento cancelado não pode ser finalizado.");
    }

    @Override
    public void cancelar(Atendimento atendimento) {
        throw new TransicaoInvalidaException("Atendimento já está cancelado.");
    }

    @Override
    public String getNome() {
        return "Cancelado";
    }
}
