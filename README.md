# Sistema de Atendimento de Clínica Veterinária

Sistema Java para registro de atendimentos veterinários, com suporte a transições de situação, avisos automáticos e regras de valor combináveis.

## Estrutura

- `domain/` — entidades principais (`Atendimento`, `Animal`, `Tutor`, `ServicoVeterinario`)
- `situacao/` — padrão **State** para controle de transições
- `aviso/` — padrão **Observer** para notificações automáticas
- `valor/` — padrão **Strategy** para acréscimos e descontos
- `docs/diagrama-classes.md` — diagrama de classes em Mermaid

## Executar testes

```bash
mvn test
```

## Transições de situação

| Situação atual   | Transições permitidas      |
|------------------|----------------------------|
| Agendado         | EmAtendimento, Cancelado   |
| EmAtendimento    | Finalizado                 |
| Finalizado       | nenhuma                    |
| Cancelado        | nenhuma                    |

## Avisos automáticos

| Evento                    | Interessado   |
|---------------------------|---------------|
| Início do atendimento     | Tutor         |
| Cancelamento              | Veterinário   |
| Finalização               | Recepção      |

Novos observadores podem ser registrados em `Atendimento.registrarObservador()` sem alterar a classe.

## Regras de valor

- `DescontoAnimalAdotado` — desconto percentual para animais adotados
- `TaxaAtendimentoDomiciliar` — taxa fixa para atendimento em domicílio
- `ServicoBanhoPosConsulta` — acréscimo opcional de banho pós-consulta

As regras são aplicadas em sequência sobre o valor base.
