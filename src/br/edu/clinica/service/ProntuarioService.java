package br.edu.clinica.service;

import br.edu.clinica.memento.HistoricoProntuarioCaretaker;
import br.edu.clinica.memento.ProntuarioOriginator;

import java.util.List;

/**
 * Serviço de Prontuário refatorado com o padrão Memento (Originator + Memento + Caretaker).
 */
public class ProntuarioService {
    private final ProntuarioOriginator prontuario = new ProntuarioOriginator();
    private final HistoricoProntuarioCaretaker caretaker = new HistoricoProntuarioCaretaker();

    public void escrever(String texto) {
        escrever(texto, "Dr. Carlos (CRM-1234)");
    }

    public void escrever(String texto, String medicoResponsavel) {
        caretaker.salvarEstadoAtual(prontuario);
        prontuario.atualizarRegistro(texto, medicoResponsavel);
        System.out.println("[Service - Memento] Prontuário atualizado por "
                + prontuario.getMedicoResponsavel() + ": " + prontuario.getTextoAtual());
    }

    public void desfazer() {
        if (caretaker.desfazerUltimaAlteracao(prontuario)) {
            System.out.println("[Service - Memento] (undo) Estado restaurado para: "
                    + prontuario.getTextoAtual() + " [Autor: " + prontuario.getMedicoResponsavel() + "]");
        } else {
            System.out.println("[Service - Memento] Nenhum snapshot anterior disponível.");
        }
    }

    public String getTextoAtual() {
        return prontuario.getTextoAtual();
    }

    public String getMedicoResponsavelAtual() {
        return prontuario.getMedicoResponsavel();
    }

    public List<String> listarSnapshotsSalvos() {
        return caretaker.listarMetadadosHistorico();
    }
}
