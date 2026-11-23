package br.edu.clinica.command;

/**
 * Interface Command (Padrão Command - GoF).
 * Responsável: Iago Mauricio (Grupo 049)
 */
public interface ConsultaCommand {
    void executar();
    void desfazer();
    String getDescricao();
}
