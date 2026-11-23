package br.edu.clinica.observer;

/**
 * Interface Observer (Subscriber) do padrão Observer.
 * Define o contrato para setores ou serviços que reagem a eventos clínicos.
 */
public interface ClinicaObserver {
    void atualizar(EventoClinico evento);
    String getNomeObservador();
}
