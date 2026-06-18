package com.clinica;

import com.clinica.aviso.RecepcaoNotificador;
import com.clinica.aviso.TutorNotificador;
import com.clinica.aviso.VeterinarioNotificador;
import com.clinica.domain.Animal;
import com.clinica.domain.Atendimento;
import com.clinica.domain.ServicoVeterinario;
import com.clinica.domain.Tutor;
import com.clinica.situacao.Cancelado;
import com.clinica.situacao.EmAtendimento;
import com.clinica.situacao.Finalizado;
import com.clinica.situacao.TransicaoInvalidaException;
import com.clinica.valor.DescontoAnimalAdotado;
import com.clinica.valor.ServicoBanhoPosConsulta;
import com.clinica.valor.TaxaAtendimentoDomiciliar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AtendimentoTest {

    private Tutor tutor;
    private Animal animalAdotado;
    private ServicoVeterinario servico;
    private TutorNotificador tutorNotificador;
    private VeterinarioNotificador veterinarioNotificador;
    private RecepcaoNotificador recepcaoNotificador;

    @BeforeEach
    void setUp() {
        tutor = new Tutor("Maria Silva", "11999990000", "maria@email.com");
        animalAdotado = new Animal("Thor", "Cão", true);
        servico = new ServicoVeterinario("Consulta geral", new BigDecimal("100.00"));

        tutorNotificador = new TutorNotificador();
        veterinarioNotificador = new VeterinarioNotificador();
        recepcaoNotificador = new RecepcaoNotificador();
    }

    private Atendimento criarAtendimento() {
        Atendimento atendimento = new Atendimento(1L, tutor, animalAdotado, servico, new BigDecimal("100.00"));
        atendimento.registrarObservador(tutorNotificador);
        atendimento.registrarObservador(veterinarioNotificador);
        atendimento.registrarObservador(recepcaoNotificador);
        return atendimento;
    }

    @Test
    void devePermitirTransicaoValidaDeAgendadoParaEmAtendimentoEFinalizado() {
        Atendimento atendimento = criarAtendimento();

        assertEquals("Agendado", atendimento.getSituacao().getNome());

        atendimento.iniciar();
        assertTrue(atendimento.getSituacao() instanceof EmAtendimento);

        atendimento.finalizar();
        assertTrue(atendimento.getSituacao() instanceof Finalizado);
    }

    @Test
    void devePermitirCancelamentoQuandoAgendado() {
        Atendimento atendimento = criarAtendimento();

        atendimento.cancelar();

        assertTrue(atendimento.getSituacao() instanceof Cancelado);
    }

    @Test
    void deveRejeitarCancelamentoQuandoFinalizado() {
        Atendimento atendimento = criarAtendimento();
        atendimento.iniciar();
        atendimento.finalizar();

        TransicaoInvalidaException excecao = assertThrows(TransicaoInvalidaException.class, atendimento::cancelar);

        assertTrue(excecao.getMessage().contains("finalizado"));
        assertTrue(atendimento.getSituacao() instanceof Finalizado);
    }

    @Test
    void deveRejeitarFinalizacaoDiretaQuandoAgendado() {
        Atendimento atendimento = criarAtendimento();

        assertThrows(TransicaoInvalidaException.class, atendimento::finalizar);
        assertEquals("Agendado", atendimento.getSituacao().getNome());
    }

    @Test
    void deveAvisarTutorQuandoAtendimentoForIniciado() {
        Atendimento atendimento = criarAtendimento();

        atendimento.iniciar();

        assertEquals(1, tutorNotificador.getAvisosEnviados().size());
        assertTrue(tutorNotificador.getAvisosEnviados().get(0).contains("Maria Silva"));
        assertTrue(tutorNotificador.getAvisosEnviados().get(0).contains("Thor"));
    }

    @Test
    void deveAvisarVeterinarioQuandoAtendimentoForCancelado() {
        Atendimento atendimento = criarAtendimento();

        atendimento.cancelar();

        assertEquals(1, veterinarioNotificador.getAvisosEnviados().size());
        assertTrue(veterinarioNotificador.getAvisosEnviados().get(0).contains("cancelado"));
    }

    @Test
    void deveAvisarRecepcaoQuandoAtendimentoForFinalizado() {
        Atendimento atendimento = criarAtendimento();

        atendimento.iniciar();
        atendimento.finalizar();

        assertEquals(1, recepcaoNotificador.getAvisosEnviados().size());
        assertTrue(recepcaoNotificador.getAvisosEnviados().get(0).contains("finalizado"));
    }

    @Test
    void deveCalcularValorFinalComMultiplasRegrasAplicadas() {
        Atendimento atendimento = criarAtendimento();

        atendimento.adicionarRegraValor(new DescontoAnimalAdotado(new BigDecimal("10")));
        atendimento.adicionarRegraValor(new TaxaAtendimentoDomiciliar(new BigDecimal("50.00"), true));
        atendimento.adicionarRegraValor(new ServicoBanhoPosConsulta(new BigDecimal("30.00"), true));

        // 100,00 - 10% = 90,00 + 50,00 (domiciliar) + 30,00 (banho) = 170,00
        assertEquals(new BigDecimal("170.00"), atendimento.getValorFinal());
    }
}
