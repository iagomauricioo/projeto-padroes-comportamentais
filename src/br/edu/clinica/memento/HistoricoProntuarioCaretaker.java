package br.edu.clinica.memento;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Caretaker do padrão Memento.
 * Armazena a pilha de snapshots do prontuário sem acessar ou modificar
 * diretamente o estado interno protegido do Memento.
 */
public class HistoricoProntuarioCaretaker {
    private final Deque<ProntuarioMemento> pilhaSnapshots = new ArrayDeque<>();

    public void salvarEstadoAtual(ProntuarioOriginator originator) {
        pilhaSnapshots.push(originator.salvarSnapshot());
    }

    public boolean desfazerUltimaAlteracao(ProntuarioOriginator originator) {
        if (pilhaSnapshots.isEmpty()) {
            return false;
        }
        ProntuarioMemento snapshotAnterior = pilhaSnapshots.pop();
        originator.restaurarSnapshot(snapshotAnterior);
        return true;
    }

    public List<String> listarMetadadosHistorico() {
        List<String> metadados = new ArrayList<>();
        for (ProntuarioMemento m : pilhaSnapshots) {
            metadados.add(m.getDescricaoMetadata());
        }
        return metadados;
    }

    public int getQuantidadeSnapshots() {
        return pilhaSnapshots.size();
    }
}
