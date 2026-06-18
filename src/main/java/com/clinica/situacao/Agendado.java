package com.clinica.situacao;

import com.clinica.domain.Atendimento;

public class Agendado implements SituacaoAtendimento {

    @Override
    public void iniciar(Atendimento atendimento) {
        atendimento.alterarSituacao(new EmAtendimento());
    }

    @Override
    public void finalizar(Atendimento atendimento) {
        throw new TransicaoInvalidaException(
                "Atendimento agendado não pode ser finalizado diretamente. Inicie o atendimento primeiro.");
    }

    @Override
    public void cancelar(Atendimento atendimento) {
        atendimento.alterarSituacao(new Cancelado());
    }

    @Override
    public String getNome() {
        return "Agendado";
    }
}
