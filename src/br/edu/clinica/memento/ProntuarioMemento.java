package br.edu.clinica.memento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Memento imutável que armazena um snapshot do estado interno do Prontuário.
 * Protege o conteúdo com visibilidade de pacote para o Originator,
 * expondo apenas metadados seguros de leitura para o Caretaker.
 */
public final class ProntuarioMemento {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final String textoSnapshot;
    private final String medicoResponsavel;
    private final LocalDateTime dataHoraSnapshot;

    ProntuarioMemento(String textoSnapshot, String medicoResponsavel, LocalDateTime dataHoraSnapshot) {
        this.textoSnapshot = textoSnapshot;
        this.medicoResponsavel = medicoResponsavel;
        this.dataHoraSnapshot = dataHoraSnapshot;
    }

    String getTextoSnapshot() {
        return textoSnapshot;
    }

    String getMedicoResponsavel() {
        return medicoResponsavel;
    }

    public String getDescricaoMetadata() {
        String resumo = textoSnapshot.isEmpty() ? "<vazio>" : textoSnapshot;
        return String.format("Snapshot[%s | Autor: %s | Conteúdo: \"%s\"]",
                dataHoraSnapshot.format(FMT), medicoResponsavel, resumo);
    }
}
