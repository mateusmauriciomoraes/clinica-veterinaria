package com.clinica.domain;

import com.clinica.aviso.NotificadorAtendimento;
import com.clinica.aviso.ObservadorAtendimento;
import com.clinica.situacao.Agendado;
import com.clinica.situacao.SituacaoAtendimento;
import com.clinica.valor.CalculadoraValorFinal;
import com.clinica.valor.RegraValor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Atendimento {

    private final Long id;
    private final Tutor tutor;
    private final Animal animal;
    private final ServicoVeterinario servico;
    private final BigDecimal valorBase;
    private final List<RegraValor> regrasValor = new ArrayList<>();
    private final NotificadorAtendimento notificador;
    private final CalculadoraValorFinal calculadoraValorFinal = new CalculadoraValorFinal();

    private SituacaoAtendimento situacao = new Agendado();

    public Atendimento(Long id, Tutor tutor, Animal animal, ServicoVeterinario servico, BigDecimal valorBase) {
        this(id, tutor, animal, servico, valorBase, new NotificadorAtendimento());
    }

    public Atendimento(Long id, Tutor tutor, Animal animal, ServicoVeterinario servico,
                       BigDecimal valorBase, NotificadorAtendimento notificador) {
        this.id = id;
        this.tutor = tutor;
        this.animal = animal;
        this.servico = servico;
        this.valorBase = valorBase;
        this.notificador = notificador;
    }

    public void registrarObservador(ObservadorAtendimento observador) {
        notificador.registrar(observador);
    }

    public void adicionarRegraValor(RegraValor regra) {
        regrasValor.add(regra);
    }

    public void iniciar() {
        situacao.iniciar(this);
    }

    public void finalizar() {
        situacao.finalizar(this);
    }

    public void cancelar() {
        situacao.cancelar(this);
    }

    public void alterarSituacao(SituacaoAtendimento novaSituacao) {
        SituacaoAtendimento situacaoAnterior = situacao;
        situacao = novaSituacao;
        notificador.notificar(this, situacaoAnterior, novaSituacao);
    }

    public BigDecimal getValorFinal() {
        return calculadoraValorFinal.calcular(valorBase, regrasValor, this);
    }

    public Long getId() {
        return id;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Animal getAnimal() {
        return animal;
    }

    public ServicoVeterinario getServico() {
        return servico;
    }

    public BigDecimal getValorBase() {
        return valorBase;
    }

    public SituacaoAtendimento getSituacao() {
        return situacao;
    }

    public List<RegraValor> getRegrasValor() {
        return Collections.unmodifiableList(regrasValor);
    }
}
