package br.edu.clinica.service;

import br.edu.clinica.model.Consulta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Receiver do padrão Command para gestão de consultas clínicas.
 * Responsável: Iago Mauricio (Grupo 049)
 */
public class AtendimentoService {
    private final List<Consulta> consultasAtivas = new ArrayList<>();

    public void criarConsulta(Consulta consulta) {
        consulta.setEstado("AGENDADA");
        if (!consultasAtivas.contains(consulta)) {
            consultasAtivas.add(consulta);
        }
        System.out.println("[Receiver - AtendimentoService] Criando consulta: " + consulta);
    }

    public void alterarConsulta(Consulta consulta) {
        System.out.println("[Receiver - AtendimentoService] Alterando consulta: " + consulta);
    }

    public void cancelarConsulta(Consulta consulta) {
        consulta.setEstado("CANCELADA");
        System.out.println("[Receiver - AtendimentoService] Cancelando consulta: " + consulta);
    }

    public List<Consulta> getConsultasAtivas() {
        return Collections.unmodifiableList(consultasAtivas);
    }
}
