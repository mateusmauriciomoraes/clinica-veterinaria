package com.clinica.valor;

import com.clinica.domain.Atendimento;

import java.math.BigDecimal;

public class TaxaAtendimentoDomiciliar implements RegraValor {

    private final BigDecimal taxaFixa;
    private final boolean atendimentoDomiciliar;

    public TaxaAtendimentoDomiciliar(BigDecimal taxaFixa, boolean atendimentoDomiciliar) {
        this.taxaFixa = taxaFixa;
        this.atendimentoDomiciliar = atendimentoDomiciliar;
    }

    @Override
    public BigDecimal aplicar(BigDecimal valorAtual, Atendimento atendimento) {
        if (!atendimentoDomiciliar) {
            return valorAtual;
        }
        return valorAtual.add(taxaFixa);
    }
}
