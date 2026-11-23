package br.edu.clinica.command;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Invoker do padrão Command.
 * Enfileira comandos de agendamento, processa em lote ou imediatamente
 * e mantém pilha para desfazer operações (undo) e histórico de auditoria.
 * Responsável: Iago Mauricio (Grupo 049)
 */
public class AgendaInvoker {
    private final Queue<ConsultaCommand> filaPendentes = new LinkedList<>();
    private final Deque<ConsultaCommand> pilhaUndo = new ArrayDeque<>();
    private final List<String> historicoAuditoria = new ArrayList<>();

    public void agendarComando(ConsultaCommand comando) {
        filaPendentes.offer(comando);
        System.out.println("[Invoker] Comando adicionado à fila: " + comando.getDescricao());
    }

    public void processarFila() {
        while (!filaPendentes.isEmpty()) {
            ConsultaCommand cmd = filaPendentes.poll();
            executarImediato(cmd);
        }
    }

    public void executarImediato(ConsultaCommand comando) {
        comando.executar();
        pilhaUndo.push(comando);
        historicoAuditoria.add("EXECUTADO: " + comando.getDescricao());
    }

    public boolean desfazerUltimoComando() {
        if (pilhaUndo.isEmpty()) {
            System.out.println("[Invoker] Nenhum comando para desfazer.");
            return false;
        }
        ConsultaCommand ultimo = pilhaUndo.pop();
        ultimo.desfazer();
        historicoAuditoria.add("DESFEITO: " + ultimo.getDescricao());
        return true;
    }

    public List<String> getHistoricoAuditoria() {
        return Collections.unmodifiableList(historicoAuditoria);
    }

    public int getTamanhoHistoricoUndo() {
        return pilhaUndo.size();
    }
}
