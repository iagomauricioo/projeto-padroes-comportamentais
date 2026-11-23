package br.edu.clinica.test;

import br.edu.clinica.service.ProntuarioService;

/**
 * Testes do padrão Memento.
 * Executar: java -cp out br.edu.clinica.test.MementoTest
 */
public class MementoTest {

    private static int testes = 0;

    public static void main(String[] args) {
        System.out.println("=== TESTES - PADRÃO MEMENTO ===");
        ProntuarioService prontuario = new ProntuarioService();

        prontuario.escrever("Anamnese inicial: paciente eupneico", "Dr. Carlos");
        prontuario.escrever("Hipótese diagnóstica: virose respiratória", "Dra. Marta");
        prontuario.escrever("Rascunho equivocado de alta", "Dra. Marta");

        verificar("Texto atual deve ser a 3ª escrita",
                "Rascunho equivocado de alta".equals(prontuario.getTextoAtual()));
        verificar("Caretaker deve guardar 3 snapshots",
                prontuario.listarSnapshotsSalvos().size() == 3);

        prontuario.desfazer();
        verificar("Após 1º undo, volta para a 2ª escrita",
                "Hipótese diagnóstica: virose respiratória".equals(prontuario.getTextoAtual()));

        prontuario.desfazer();
        verificar("Após 2º undo, volta para a 1ª escrita",
                "Anamnese inicial: paciente eupneico".equals(prontuario.getTextoAtual()));
        verificar("Autor também é restaurado (Dr. Carlos)",
                "Dr. Carlos".equals(prontuario.getMedicoResponsavelAtual()));

        System.out.println("\n=== " + testes + " TESTES DO MEMENTO PASSARAM ===");
    }

    private static void verificar(String descricao, boolean condicao) {
        testes++;
        if (!condicao) throw new AssertionError("FALHOU: " + descricao);
        System.out.println("  [OK] " + descricao);
    }
}
