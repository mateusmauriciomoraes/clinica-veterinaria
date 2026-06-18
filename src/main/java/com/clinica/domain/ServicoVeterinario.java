package com.clinica.domain;

import java.math.BigDecimal;

public class ServicoVeterinario {

    private final String descricao;
    private final BigDecimal valorPadrao;

    public ServicoVeterinario(String descricao, BigDecimal valorPadrao) {
        this.descricao = descricao;
        this.valorPadrao = valorPadrao;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValorPadrao() {
        return valorPadrao;
    }
}
