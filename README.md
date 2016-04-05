# Modelagem do sistema do "Clube de Jogos"

## O Clube de Jogos

O Clube de Jogos é uma instituição, criada por Salomão Matos, que permite que pessoas matriculadas possam exercer atividades
lúdicas (jogos) de diversos tipos e modalidades. Segundo seu criador, “Os jogos agregam a vida cotidiana situações nas quais
podemos experimentar momentos de tensão e alegria, por meio de regras bem definidas e consentidas”.

A motivação de Salomão para criar o clube foi a percepção de que as pessoas começam a abrir mão de atividades de recreação,
diversão e de convívio social conforme vão envelhecendo. Salomão diz, “As pessoas necessitam de espaços como esse,
para que possam não só praticar as atividades, mas também lembrar como interações sociais dessa forma, com intuito de
diversão são importantes nas nossas vidas”.

O clube permite que usuários matriculados se inscrevam em turmas de diversos jogos diferentes. Os jogos podem ser tanto
físicos (como esportes), eletrônicos (como computador e videogames) ou analógicos (como jogos de cartas, tabuleiro).
Para estas atividades, o clube conta com um espaço bem definido. São duas salas de atividades e duas quadras poliesportivas.

Para organizar as atividades existe um calendário de agendamento. As atividades são planejadas sempre com antecedência para
que todos os matriculados possam se programar de acordo com sua disponibilidade. Toda semana o clube não abre durante um dia,
devido à folga para os funcionários e limpeza dos locais de atividades.

As turmas possuem características que as de nem como únicas, tais como:
* atividade;
* duração;
* data de realização;
* quantidade de usuários; e
* qual o espaço ocupado.

Além disso, em todos os meses são computados pontos que formam um ranking anual dos usuários e equipes com mais pontos.
Cada atividade praticada gera uma pontuação para a equipe ou o usuário. Os pontos variam de acordo com a atividade e depois
são totalizados de forma que uma partida de determinada modalidade valha pontos equivalentes a uma partida de uma
modalidade totalmente diferente (por exemplo, uma partida de futebol pode valer por cinco partidas de pôquer, um jogo de
cartas, e a mesma partida de futebol equivale a duas partidas de xadrez, um jogo de tabuleiro). Ao final do ano, recebem o
título de ”Rei dos Jogos” a equipe e o usuário que tiverem mais pontos ao longo do ano.

O clube é regido por regras que definem o uso das salas, o agendamento das turmas, quais turmas que participarão das
atividades, entre outros. As regras serão especificadas em outro momento.

## Problema

Nós iremos desenvolver o sistema de gerenciamento do Clube de Jogos para o Sr. Salomão Matos. Ele tem uma boa ideia do que
o sistema deve fazer, mas a especificação completa do sistema ainda não está muito clara para ele. Por isso, a medida que o
Sr. Matos entender melhor o problema, novos EPs serão lançados.

Usando a linguagem de programação Java e os conceitos vistos na disciplina, realize a modelagem do sistema que gerencia o
Clube de Jogos e implemente esse sistema de acordo com a descrição acima. Você deverá fornecer as classes para que todas
as entidades pertencentes ao clube, de acordo com sua modelagem, se comuniquem entre si. **O sistema deve ser capaz de
agendar as salas e suas turmas em dias e horários determinados, sem que haja conflitos (de horários, participantes e local)**.


Tenha sempre em mente que o seu código será utilizado nos próximos EPs, ou seja, ele deve tentar modelar o sistema da
melhor forma possível (com as informações que vocês possuem atualmente). É claro que o código poderá (e deverá) ser
refatorado e alterado (incluindo o nome das classes) no futuro, a medida que os novos requisitos forem sendo especificados.

Você pode criar quantas classes achar necessário, desde que sejam pertinentes à sua modelagem e à descrição do sistema,
considerando as regras de negócio a seguir.

Escreva um relatório que descreva as entidades identificadas e a sua abordagem para resolução do problema. O relatório
também deve conter claramente o nome e número USP dos integrantes do grupo.

## Regras do Clube de Jogos

O clube possui regras bem definidas para o seu funcionamento. Essas regras deverão ser implementadas de acordo com sua
modelagem do sistema.

1. Existem três modalidades diferentes de jogos: digitais, analógicos e físicos. Todas as atividades serão categorizadas
por um desses tipos. Podemos citar como exemplo de cada modalidade as atividades: videogame, xadrez e vôlei. Podem existir
diversas atividades.

