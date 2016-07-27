Operações com datas
===================

Resoluções fazem uso significativo de operações com datas.
As operações e resultados esperados são definidos conforme
abaixo.

Mês
---

É comum interpretar o período de um mês como sendo o período
de 30 dias. Por outro lado, se alguém diz que um docente
orientou um estudante por um período de um mês, iniciado no
primeiro dia do mês, então a interpretação aqui adotada é de
que a orientação foi desde o dia primeiro até o último dia do
mês, inclusive, independente se o mês é de 28, 29, 30 ou 31
dias.

Um mês contado a partir de um dia D,
é o período que vai do dia D até o dia
D - 1 do mês seguinte, inclusive. Naturalmente,
essa mesma regra se aplica para o mês seguinte e
assim sucessivamente.

Convém observar que a data fornecida como "Fim"
na tabela abaixo indica o término e não faz parte
do período em questão.

* Meses a partir de uma dada data
     |Início    |Meses|Fim       |
     |----------|-----|----------|
     |01/02/2015|1    |01/03/2015|
     |01/02/2016|1    |01/03/2016|
     |01/04/2016|1    |01/05/2016|
     |01/03/2016|1    |01/04/2016|
     |02/02/2015|1    |02/03/2015|
     |28/02/2015|1    |28/03/2015|
     |10/02/2015|36   |10/02/2018|

Exclusão de períodos incluídos
------------------------------

Quando se deseja definir quando termina um determinado período
de meses, por exemplo, iniciado a partir de uma dada data, excluindo-se
alguns períodos (de afastamento, licença, ...) o correto é
estabelecer a data sem a exclusão de períodos e, na sequência,
adicionar a quantidade de dias de afastamento à data obtida.
Por exemplo, se um estágio probatório é iniciado em 01/02/2015,
o término dele é previsto para 01/02/2018, caso não ocorra nenhuma
interrupção. Se houver uma interrupção (licença) de um único dia,
por exemplo, no dia 01/02/2015, então o término ocorrerá em 02/02/2018.

* "36" meses após "01/02/2015" termina em "02/02/2018"
    |InicioExclusao|FimExclusao|
    |01/02/2015|02/02/2015|

Exclusão de períodos (ignorar fora do período relevante
-------------------------------------------------------

Naturalmente, um período excluído é aquele que se inicia antes
do término provável, sem interrupção. Contudo, não é o único caso.
Por exemplo, nos 36 primeiros meses talvez sejam computados 10
meses de efetivo trabalho e, nesse caso, dentre muitos outros cenários,
um novo afastamento pode ser iniciado após esses 36 meses mas ainda
dentro do estágio probatório.


* "36" meses após "01/02/2015" termina em "01/02/2018"
    |InicioExclusao|FimExclusao|
    |01/02/2018|02/02/2018|
