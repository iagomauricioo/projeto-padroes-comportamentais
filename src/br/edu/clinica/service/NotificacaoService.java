package br.edu.clinica.service;

import br.edu.clinica.observer.ClinicaObserver;
import br.edu.clinica.observer.ClinicaSubject;
import br.edu.clinica.observer.EventoClinico;

import java.util.ArrayList;
import java.util.List;

/**
 * ConcreteSubject do padrão Observer para disparo de eventos clínicos aos setores inscritos.
 */
public class NotificacaoService implements ClinicaSubject {
    private final List<ClinicaObserver> observadores = new ArrayList<>();

    @Override
    public void adicionarObservador(ClinicaObserver obs) {
        if (!observadores.contains(obs)) {
            observadores.add(obs);
            System.out.println("[Subject - NotificacaoService] Observador inscrito: " + obs.getNomeObservador());
        }
    }

    @Override
    public void removerObservador(ClinicaObserver obs) {
        if (observadores.remove(obs)) {
            System.out.println("[Subject - NotificacaoService] Observador removido: " + obs.getNomeObservador());
        }
    }

    @Override
    public void notificarObservadores(EventoClinico evento) {
        System.out.println("[Subject - NotificacaoService] Notificando " + observadores.size()
                + " observador(es) sobre: " + evento);
        for (ClinicaObserver obs : new ArrayList<>(observadores)) {
            obs.atualizar(evento);
        }
    }

    public void notificarMudanca(String descricaoEvento) {
        notificarObservadores(new EventoClinico("MUDANCA_ESTADO_CONSULTA", descricaoEvento, "Paciente Geral"));
    }

    public int getTotalObservadores() {
        return observadores.size();
    }
}
