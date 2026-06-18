package com.clinica.valor;

import com.clinica.domain.Atendimento;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DescontoAnimalAdotado implements RegraValor {

    private final BigDecimal percentualDesconto;

    public DescontoAnimalAdotado(BigDecimal percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }

    @Override
    public BigDecimal aplicar(BigDecimal valorAtual, Atendimento atendimento) {
        if (!atendimento.getAnimal().isAdotado()) {
            return valorAtual;
        }

        BigDecimal desconto = valorAtual.multiply(percentualDesconto)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return valorAtual.subtract(desconto);
    }
}
