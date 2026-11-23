package br.edu.clinica.command;

import br.edu.clinica.model.Consulta;
import br.edu.clinica.service.AtendimentoService;

/**
 * ConcreteCommand para cancelamento de consulta.
 * Armazena o estado prévio da consulta para suportar reversão (undo).
 * Responsável: Iago Mauricio (Grupo 049)
 */
public class CancelarConsultaCommand implements ConsultaCommand {
    private final AtendimentoService receiver;
    private final Consulta consulta;
    private String estadoAnterior;

    public CancelarConsultaCommand(AtendimentoService receiver, Consulta consulta) {
        this.receiver = receiver;
        this.consulta = consulta;
    }

    @Override
    public void executar() {
        this.estadoAnterior = consulta.getEstado();
        receiver.cancelarConsulta(consulta);
    }

    @Override
    public void desfazer() {
        if (estadoAnterior != null) {
            System.out.println("[Command-Undo] Revertendo cancelamento; estado restaurado para: " + estadoAnterior);
            consulta.setEstado(estadoAnterior);
        }
    }

    @Override
    public String getDescricao() {
        return "CancelarConsulta[" + consulta.getPaciente().getNome() + "]";
    }
}
