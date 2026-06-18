package com.clinica.valor;

import com.clinica.domain.Atendimento;

import java.math.BigDecimal;
import java.util.List;

public class CalculadoraValorFinal {

    public BigDecimal calcular(BigDecimal valorBase, List<RegraValor> regras, Atendimento atendimento) {
        BigDecimal valor = valorBase;
        for (RegraValor regra : regras) {
            valor = regra.aplicar(valor, atendimento);
        }
        return valor;
    }
}
