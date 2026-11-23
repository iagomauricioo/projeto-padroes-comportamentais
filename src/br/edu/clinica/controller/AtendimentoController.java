package br.edu.clinica.controller;

import br.edu.clinica.command.AgendaInvoker;
import br.edu.clinica.command.AlterarConsultaCommand;
import br.edu.clinica.command.CancelarConsultaCommand;
import br.edu.clinica.command.CriarConsultaCommand;
import br.edu.clinica.model.*;
import br.edu.clinica.service.*;
import br.edu.clinica.view.ConsoleView;

import java.time.LocalDateTime;

public class AtendimentoController {

    private final ConsoleView view = new ConsoleView();
    private final AtendimentoService atendimentoService = new AtendimentoService();
    private final EncaminhamentoService encaminhamentoService = new EncaminhamentoService();
    private final FilaService filaService = new FilaService();
    private final ProntuarioService prontuarioService = new ProntuarioService();
    private final EstadoConsultaService estadoService = new EstadoConsultaService();
    private final EstrategiaAtendimentoService estrategiaService = new EstrategiaAtendimentoService();

    public void registrarConsulta(){
        view.titulo("Command — Registrar/Alterar/Cancelar (Iago Mauricio)");
        var p = new Paciente("Ana", "000.111.222-33");
        var m = new Medico("Carlos", "CRM-1234", "Clínico");
        var dataInicial = LocalDateTime.now().plusDays(1).withHour(14).withMinute(0).withSecond(0).withNano(0);
        var c = new Consulta(p, m, dataInicial);

        var invoker = new AgendaInvoker();
        invoker.agendarComando(new CriarConsultaCommand(atendimentoService, c));
        invoker.agendarComando(new AlterarConsultaCommand(atendimentoService, c, dataInicial.plusHours(2)));
        invoker.agendarComando(new CancelarConsultaCommand(atendimentoService, c));

        invoker.processarFila();
        // Demonstra o desfazer (undo) do cancelamento
        invoker.desfazerUltimoComando();
        view.info("Estado final da consulta após undo do cancelamento: " + c.getEstado());
        view.linha();
    }

    public void encaminharPaciente(){
        view.titulo("Chain of Responsibility — Encaminhamento");
        var p = new Paciente("Bruno", "111.222.333-44");
        encaminhamentoService.encaminhar(p);
        view.linha();
    }

    public void listarPacientesFila(){
        view.titulo("Iterator — Fila de Pacientes");
        filaService.adicionar(new Paciente("Clara","222"));
        filaService.adicionar(new Paciente("Diego","333"));
        filaService.listar();
        view.linha();
    }

    public void historicoProntuario(){
        view.titulo("Memento — Histórico / Desfazer");
        prontuarioService.escrever("Entrada 1: sinais vitais estáveis (PA 120/80)", "Dr. Carlos");
        prontuarioService.escrever("Entrada 2: suspeita clínica registrada e prescrição adicionada", "Dra. Marta");
        prontuarioService.escrever("Entrada 3: anotação incorreta inserida por engano", "Dra. Marta");

        view.info("Snapshots armazenados no Caretaker: " + prontuarioService.listarSnapshotsSalvos().size());
        prontuarioService.desfazer(); // desfaz Entrada 3 -> volta para Entrada 2
        prontuarioService.desfazer(); // desfaz Entrada 2 -> volta para Entrada 1
        view.linha();
    }

    public void fluxoEstadosConsulta(){
        view.titulo("State — Ciclo de Vida da Consulta");
        var c = new Consulta(new Paciente("Eva","444"), new Medico("Marta","CRM-4321","Pediatra"),
                LocalDateTime.now().plusHours(3));
        estadoService.avancar(c);
        estadoService.avancar(c);
        estadoService.cancelar(c);
        view.linha();
    }

    public void atendimentoComEstrategia(){
        view.titulo("Strategy — Modos de Atendimento");
        var p = new Paciente("Igor","555");
        estrategiaService.atenderPresencial(p);
        estrategiaService.atenderTelemedicina(p);
        estrategiaService.atenderEmergencia(p);
        view.linha();
    }
}
