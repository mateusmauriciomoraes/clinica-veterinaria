package com.clinica.aviso;

import com.clinica.domain.Atendimento;
import com.clinica.situacao.SituacaoAtendimento;

import java.util.ArrayList;
import java.util.List;

public class NotificadorAtendimento {

    private final List<ObservadorAtendimento> observadores = new ArrayList<>();

    public void registrar(ObservadorAtendimento observador) {
        observadores.add(observador);
    }

    public void notificar(Atendimento atendimento, SituacaoAtendimento situacaoAnterior, SituacaoAtendimento situacaoAtual) {
        for (ObservadorAtendimento observador : observadores) {
            if (observador.interessa(situacaoAnterior, situacaoAtual)) {
                observador.atualizar(atendimento, situacaoAnterior, situacaoAtual);
            }
        }
    }
}
