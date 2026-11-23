package br.edu.clinica.controller;

import br.edu.clinica.observer.EnfermagemObserver;
import br.edu.clinica.observer.EventoClinico;
import br.edu.clinica.observer.FarmaciaObserver;
import br.edu.clinica.observer.PainelAuditoriaObserver;
import br.edu.clinica.service.ComunicacaoService;
import br.edu.clinica.service.NotificacaoService;
import br.edu.clinica.view.ConsoleView;

public class NotificacaoController {
    private final ConsoleView view = new ConsoleView();
    private final ComunicacaoService comunicacaoService = new ComunicacaoService();
    private final NotificacaoService notificacaoService = new NotificacaoService();

    public void comunicarEntreSetores(){
        view.titulo("Mediator — Comunicação entre setores");
        comunicacaoService.enviar("Recepcao", "Laboratorio", "Paciente aguardando coleta.");
        comunicacaoService.enviar("Laboratorio", "Farmacia", "Exame liberado; preparar medicação.");
        view.linha();
    }

    public void dispararNotificacoes(){
        view.titulo("Observer — Eventos e Assinantes");
        var enfermagem = new EnfermagemObserver();
        var farmacia = new FarmaciaObserver();
        var auditoria = new PainelAuditoriaObserver();

        notificacaoService.adicionarObservador(enfermagem);
        notificacaoService.adicionarObservador(farmacia);
        notificacaoService.adicionarObservador(auditoria);

        notificacaoService.notificarObservadores(
                new EventoClinico("INICIO_ATENDIMENTO", "Consulta entrou em EM_ATENDIMENTO", "Ana (000.111.222-33)")
        );

        notificacaoService.removerObservador(farmacia);
        notificacaoService.notificarObservadores(
                new EventoClinico("ALTA_CLINICA", "Consulta CONCLUIDA e paciente liberado", "Ana (000.111.222-33)")
        );
        view.linha();
    }
}
