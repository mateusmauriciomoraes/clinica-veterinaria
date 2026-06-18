package com.clinica.valor;

import com.clinica.domain.Atendimento;

import java.math.BigDecimal;

public interface RegraValor {

    BigDecimal aplicar(BigDecimal valorAtual, Atendimento atendimento);
}
