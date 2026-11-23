# MediClinic — AV3 Padrões Comportamentais (Grupo 049)

Padrões sorteados: Command, Memento, Observer e Visitor.

## Integrantes

| Integrante | Padrão |
| :--- | :--- |
| Iago Mauricio | Command |
| Victor Hugo Calheiros | Memento |
| João Vitor Correia Araújo | Observer |
| Yann Fellype | Visitor |

## Como executar

Requer JDK 17+.

```bash
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out br.edu.clinica.main.ClinicaMain
```

## Command — Iago Mauricio

Antes, o `AtendimentoController` chamava direto os métodos de criar, alterar e cancelar consulta do `AtendimentoService`, sem fila, histórico ou forma de desfazer.

- `command/ConsultaCommand`: interface do comando (`executar`, `desfazer`)
- `CriarConsultaCommand`, `AlterarConsultaCommand`, `CancelarConsultaCommand`: comandos concretos
- `command/AgendaInvoker`: fila de comandos, pilha de undo e histórico
- `service/AtendimentoService`: receiver

Teste:

```bash
java -cp out br.edu.clinica.test.CommandTest
```
