package br.edu.clinica.observer;

/**
 * ConcreteObserver representando a Farmácia Interna da clínica.
 */
public class FarmaciaObserver implements ClinicaObserver {
    private int notificacoesRecebidas = 0;

    @Override
    public void atualizar(EventoClinico evento) {
        notificacoesRecebidas++;
        System.out.println("[Observer - Farmácia] Separando insumos para evento -> " + evento);
    }

    @Override
    public String getNomeObservador() {
        return "Farmácia";
    }

    public int getNotificacoesRecebidas() {
        return notificacoesRecebidas;
    }
}
