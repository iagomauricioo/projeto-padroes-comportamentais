package br.edu.clinica.test;

import br.edu.clinica.observer.EnfermagemObserver;
import br.edu.clinica.observer.EventoClinico;
import br.edu.clinica.observer.FarmaciaObserver;
import br.edu.clinica.observer.PainelAuditoriaObserver;
import br.edu.clinica.service.NotificacaoService;

/**
 * Testes do padrão Observer.
 * Executar: java -cp out br.edu.clinica.test.ObserverTest
 */
public class ObserverTest {

    private static int testes = 0;

    public static void main(String[] args) {
        System.out.println("=== TESTES - PADRÃO OBSERVER ===");
        NotificacaoService subject = new NotificacaoService();
        EnfermagemObserver enfermagem = new EnfermagemObserver();
        FarmaciaObserver farmacia = new FarmaciaObserver();
        PainelAuditoriaObserver auditoria = new PainelAuditoriaObserver();

        subject.adicionarObservador(enfermagem);
        subject.adicionarObservador(farmacia);
        subject.adicionarObservador(auditoria);
        verificar("3 observadores inscritos", subject.getTotalObservadores() == 3);

        subject.notificarObservadores(new EventoClinico("TRIAGEM", "Paciente classificado como urgente", "Bruno"));
        verificar("Enfermagem recebeu 1 notificação", enfermagem.getNotificacoesRecebidas() == 1);
        verificar("Farmácia recebeu 1 notificação", farmacia.getNotificacoesRecebidas() == 1);
        verificar("Auditoria registrou 1 log", auditoria.getLogsRegistrados().size() == 1);

        subject.removerObservador(farmacia);
        subject.notificarObservadores(new EventoClinico("ALTA", "Paciente recebeu alta", "Bruno"));
        verificar("Enfermagem recebeu 2 notificações", enfermagem.getNotificacoesRecebidas() == 2);
        verificar("Farmácia removida continua com 1", farmacia.getNotificacoesRecebidas() == 1);
        verificar("Auditoria registrou 2 logs", auditoria.getLogsRegistrados().size() == 2);

        System.out.println("\n=== " + testes + " TESTES DO OBSERVER PASSARAM ===");
    }

    private static void verificar(String descricao, boolean condicao) {
        testes++;
        if (!condicao) throw new AssertionError("FALHOU: " + descricao);
        System.out.println("  [OK] " + descricao);
    }
}
