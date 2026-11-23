package br.edu.clinica.test;

import br.edu.clinica.command.AgendaInvoker;
import br.edu.clinica.command.AlterarConsultaCommand;
import br.edu.clinica.command.CancelarConsultaCommand;
import br.edu.clinica.command.CriarConsultaCommand;
import br.edu.clinica.model.Consulta;
import br.edu.clinica.model.Medico;
import br.edu.clinica.model.Paciente;
import br.edu.clinica.service.AtendimentoService;

import java.time.LocalDateTime;

/**
 * Testes do padrão Command (sem dependências externas).
 * Responsável: Iago Mauricio (Grupo 049)
 *
 * Executar: java -cp out br.edu.clinica.test.CommandTest
 */
public class CommandTest {

    private static int testes = 0;

    public static void main(String[] args) {
        System.out.println("=== TESTES - PADRÃO COMMAND ===");
        AtendimentoService receiver = new AtendimentoService();
        AgendaInvoker invoker = new AgendaInvoker();

        LocalDateTime horarioOriginal = LocalDateTime.of(2026, 11, 25, 10, 0);
        LocalDateTime novoHorario = LocalDateTime.of(2026, 11, 25, 15, 30);
        Consulta consulta = new Consulta(
                new Paciente("Ana", "000.111.222-33"),
                new Medico("Carlos", "CRM-1234", "Clínico"),
                horarioOriginal
        );

        invoker.agendarComando(new CriarConsultaCommand(receiver, consulta));
        invoker.agendarComando(new AlterarConsultaCommand(receiver, consulta, novoHorario));
        invoker.agendarComando(new CancelarConsultaCommand(receiver, consulta));
        verificar("Nada executa antes de processar a fila", receiver.getConsultasAtivas().isEmpty());

        invoker.processarFila();
        verificar("Consulta deve estar CANCELADA após processar fila", "CANCELADA".equals(consulta.getEstado()));
        verificar("Horário deve ter sido alterado para 15:30", novoHorario.equals(consulta.getDataHora()));
        verificar("Pilha de undo deve ter 3 comandos", invoker.getTamanhoHistoricoUndo() == 3);

        invoker.desfazerUltimoComando();
        verificar("Após 1º undo, estado volta para AGENDADA", "AGENDADA".equals(consulta.getEstado()));

        invoker.desfazerUltimoComando();
        verificar("Após 2º undo, horário volta para 10:00", horarioOriginal.equals(consulta.getDataHora()));
        verificar("Histórico de auditoria registra 3 execuções + 2 undos", invoker.getHistoricoAuditoria().size() == 5);

        System.out.println("\n=== " + testes + " TESTES DO COMMAND PASSARAM ===");
    }

    private static void verificar(String descricao, boolean condicao) {
        testes++;
        if (!condicao) throw new AssertionError("FALHOU: " + descricao);
        System.out.println("  [OK] " + descricao);
    }
}
