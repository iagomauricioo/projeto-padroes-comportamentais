package br.edu.clinica.observer;

import java.time.LocalDateTime;

/**
 * Objeto de evento clínico transmitido pelo Subject aos Observers inscritos.
 */
public class EventoClinico {
    private final String tipo;
    private final String descricao;
    private final String paciente;
    private final LocalDateTime dataHora;

    public EventoClinico(String tipo, String descricao, String paciente) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.paciente = paciente;
        this.dataHora = LocalDateTime.now();
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPaciente() {
        return paciente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public String toString() {
        return String.format("[%s] Paciente: %s | %s", tipo, paciente, descricao);
    }
}
