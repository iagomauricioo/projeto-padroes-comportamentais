package br.edu.clinica.command;

import br.edu.clinica.model.Consulta;
import br.edu.clinica.service.AtendimentoService;

/**
 * ConcreteCommand para criação/agendamento de consulta.
 * Responsável: Iago Mauricio (Grupo 049)
 */
public class CriarConsultaCommand implements ConsultaCommand {
    private final AtendimentoService receiver;
    private final Consulta consulta;

    public CriarConsultaCommand(AtendimentoService receiver, Consulta consulta) {
        this.receiver = receiver;
        this.consulta = consulta;
    }

    @Override
    public void executar() {
        receiver.criarConsulta(consulta);
    }

    @Override
    public void desfazer() {
        System.out.println("[Command-Undo] Desfazendo criação da consulta de " + consulta.getPaciente().getNome());
        receiver.cancelarConsulta(consulta);
    }

    @Override
    public String getDescricao() {
        return "CriarConsulta[" + consulta.getPaciente().getNome() + " - " + consulta.getDataHora() + "]";
    }
}
