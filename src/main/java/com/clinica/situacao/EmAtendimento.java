package com.clinica.situacao;

import com.clinica.domain.Atendimento;

public class EmAtendimento implements SituacaoAtendimento {

    @Override
    public void iniciar(Atendimento atendimento) {
        throw new TransicaoInvalidaException("Atendimento já está em andamento.");
    }

    @Override
    public void finalizar(Atendimento atendimento) {
        atendimento.alterarSituacao(new Finalizado());
    }

    @Override
    public void cancelar(Atendimento atendimento) {
        throw new TransicaoInvalidaException(
                "Atendimento em andamento não pode ser cancelado. Finalize o atendimento.");
    }

    @Override
    public String getNome() {
        return "EmAtendimento";
    }
}
