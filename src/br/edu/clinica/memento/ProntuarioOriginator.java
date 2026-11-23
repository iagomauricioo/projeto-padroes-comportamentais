package br.edu.clinica.memento;

import java.time.LocalDateTime;

/**
 * Originator do padrão Memento.
 * Representa o prontuário eletrônico cujo estado evolui durante o atendimento
 * e que sabe criar e restaurar snapshots imutáveis (ProntuarioMemento).
 */
public class ProntuarioOriginator {
    private String textoAtual = "";
    private String medicoResponsavel = "Sistema";

    public void atualizarRegistro(String novoTexto, String medicoResponsavel) {
        this.textoAtual = novoTexto;
        this.medicoResponsavel = medicoResponsavel;
    }

    public ProntuarioMemento salvarSnapshot() {
        return new ProntuarioMemento(this.textoAtual, this.medicoResponsavel, LocalDateTime.now());
    }

    public void restaurarSnapshot(ProntuarioMemento memento) {
        if (memento != null) {
            this.textoAtual = memento.getTextoSnapshot();
            this.medicoResponsavel = memento.getMedicoResponsavel();
        }
    }

    public String getTextoAtual() {
        return textoAtual;
    }

    public String getMedicoResponsavel() {
        return medicoResponsavel;
    }
}
