package com.clinica.aviso;

import com.clinica.domain.Atendimento;
import com.clinica.situacao.EmAtendimento;
import com.clinica.situacao.Finalizado;
import com.clinica.situacao.SituacaoAtendimento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecepcaoNotificador implements ObservadorAtendimento {

    private final List<String> avisosEnviados = new ArrayList<>();

    @Override
    public void atualizar(Atendimento atendimento, SituacaoAtendimento situacaoAnterior, SituacaoAtendimento situacaoAtual) {
        String mensagem = String.format(
                "Recepção avisada: atendimento do animal %s foi finalizado. Valor: R$ %s",
                atendimento.getAnimal().getNome(),
                atendimento.getValorFinal());
        avisosEnviados.add(mensagem);
    }

    @Override
    public boolean interessa(SituacaoAtendimento situacaoAnterior, SituacaoAtendimento situacaoAtual) {
        return situacaoAnterior instanceof EmAtendimento && situacaoAtual instanceof Finalizado;
    }

    public List<String> getAvisosEnviados() {
        return Collections.unmodifiableList(avisosEnviados);
    }
}
