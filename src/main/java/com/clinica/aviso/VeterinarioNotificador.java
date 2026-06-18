package com.clinica.aviso;

import com.clinica.domain.Atendimento;
import com.clinica.situacao.Agendado;
import com.clinica.situacao.Cancelado;
import com.clinica.situacao.SituacaoAtendimento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VeterinarioNotificador implements ObservadorAtendimento {

    private final List<String> avisosEnviados = new ArrayList<>();

    @Override
    public void atualizar(Atendimento atendimento, SituacaoAtendimento situacaoAnterior, SituacaoAtendimento situacaoAtual) {
        String mensagem = String.format(
                "Veterinário avisado: atendimento do animal %s foi cancelado.",
                atendimento.getAnimal().getNome());
        avisosEnviados.add(mensagem);
    }

    @Override
    public boolean interessa(SituacaoAtendimento situacaoAnterior, SituacaoAtendimento situacaoAtual) {
        return situacaoAnterior instanceof Agendado && situacaoAtual instanceof Cancelado;
    }

    public List<String> getAvisosEnviados() {
        return Collections.unmodifiableList(avisosEnviados);
    }
}
