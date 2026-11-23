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

## Memento — Victor Hugo Calheiros

Antes, o `ProntuarioService` guardava o texto atual e uma pilha de `String` dentro do próprio serviço, misturando o estado do prontuário com o histórico e sem guardar quem escreveu.

- `memento/ProntuarioMemento`: snapshot imutável (texto, médico, data/hora), visível só dentro do pacote
- `memento/ProntuarioOriginator`: o prontuário; cria e restaura mementos
- `memento/HistoricoProntuarioCaretaker`: guarda a pilha de mementos sem ler o conteúdo
- `service/ProntuarioService`: usa os três para escrever e desfazer

Teste:

```bash
java -cp out br.edu.clinica.test.MementoTest
```

## Observer — João Vitor Correia Araújo

Antes, o `NotificacaoService` guardava uma `List<Runnable>`: os setores eram avisados, mas não recebiam nenhuma informação sobre o evento.

- `observer/ClinicaSubject` e `observer/ClinicaObserver`: interfaces do padrão (`atualizar(EventoClinico)`)
- `observer/EventoClinico`: dados do evento (tipo, descrição, paciente, horário)
- `EnfermagemObserver`, `FarmaciaObserver`, `PainelAuditoriaObserver`: setores que reagem ao evento
- `service/NotificacaoService`: subject; inscreve, remove e notifica

Teste:

```bash
java -cp out br.edu.clinica.test.ObserverTest
```
