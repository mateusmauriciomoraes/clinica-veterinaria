package com.clinica.valor;

import com.clinica.domain.Atendimento;

import java.math.BigDecimal;

public class ServicoBanhoPosConsulta implements RegraValor {

    private final BigDecimal valorBanho;
    private final boolean incluirBanho;

    public ServicoBanhoPosConsulta(BigDecimal valorBanho, boolean incluirBanho) {
        this.valorBanho = valorBanho;
        this.incluirBanho = incluirBanho;
    }

    @Override
    public BigDecimal aplicar(BigDecimal valorAtual, Atendimento atendimento) {
        if (!incluirBanho) {
            return valorAtual;
        }
        return valorAtual.add(valorBanho);
    }
}
