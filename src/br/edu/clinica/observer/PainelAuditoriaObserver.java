package br.edu.clinica.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ConcreteObserver que grava trilha de auditoria para todos os eventos notificados.
 */
public class PainelAuditoriaObserver implements ClinicaObserver {
    private final List<String> logsRegistrados = new ArrayList<>();

    @Override
    public void atualizar(EventoClinico evento) {
        String entrada = "LOG_EVENTO: " + evento.toString();
        logsRegistrados.add(entrada);
        System.out.println("[Observer - Auditoria] Evento arquivado -> " + entrada);
    }

    @Override
    public String getNomeObservador() {
        return "PainelAuditoria";
    }

    public List<String> getLogsRegistrados() {
        return Collections.unmodifiableList(logsRegistrados);
    }
}
