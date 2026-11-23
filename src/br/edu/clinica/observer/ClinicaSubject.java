package br.edu.clinica.observer;

/**
 * Interface Subject (Publisher) do padrão Observer.
 */
public interface ClinicaSubject {
    void adicionarObservador(ClinicaObserver observador);
    void removerObservador(ClinicaObserver observador);
    void notificarObservadores(EventoClinico evento);
}
