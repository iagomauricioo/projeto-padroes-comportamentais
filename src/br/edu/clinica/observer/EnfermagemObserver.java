package br.edu.clinica.observer;

/**
 * ConcreteObserver representando o posto de Enfermagem.
 */
public class EnfermagemObserver implements ClinicaObserver {
    private int notificacoesRecebidas = 0;

    @Override
    public void atualizar(EventoClinico evento) {
        notificacoesRecebidas++;
        System.out.println("[Observer - Enfermagem] Preparando box/triagem para evento -> " + evento);
    }

    @Override
    public String getNomeObservador() {
        return "Enfermagem";
    }

    public int getNotificacoesRecebidas() {
        return notificacoesRecebidas;
    }
}
