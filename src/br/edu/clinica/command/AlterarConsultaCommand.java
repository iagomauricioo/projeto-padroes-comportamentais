package br.edu.clinica.command;

import br.edu.clinica.model.Consulta;
import br.edu.clinica.service.AtendimentoService;

import java.time.LocalDateTime;

/**
 * ConcreteCommand para reagendamento/alteração de horário de consulta.
 * Guarda o horário anterior para permitir operação de desfazer (undo).
 * Responsável: Iago Mauricio (Grupo 049)
 */
public class AlterarConsultaCommand implements ConsultaCommand {
    private final AtendimentoService receiver;
    private final Consulta consulta;
    private final LocalDateTime novaDataHora;
    private LocalDateTime dataHoraAnterior;

    public AlterarConsultaCommand(AtendimentoService receiver, Consulta consulta, LocalDateTime novaDataHora) {
        this.receiver = receiver;
        this.consulta = consulta;
        this.novaDataHora = novaDataHora;
    }

    @Override
    public void executar() {
        this.dataHoraAnterior = consulta.getDataHora();
        consulta.setDataHora(novaDataHora);
        receiver.alterarConsulta(consulta);
    }

    @Override
    public void desfazer() {
        if (dataHoraAnterior != null) {
            System.out.println("[Command-Undo] Restaurando horário anterior da consulta: " + dataHoraAnterior);
            consulta.setDataHora(dataHoraAnterior);
            receiver.alterarConsulta(consulta);
        }
    }

    @Override
    public String getDescricao() {
        return "AlterarConsulta[" + consulta.getPaciente().getNome() + " -> " + novaDataHora + "]";
    }
}
