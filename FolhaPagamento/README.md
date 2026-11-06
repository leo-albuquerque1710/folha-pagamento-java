# Sistema de Folha de Pagamento em Java

Projeto acadêmico de console, sem dependências externas. Requer **JDK 8 ou superior** (incluindo o compilador `javac`). Os arquivos usam UTF-8.

## Executar

Abra um terminal dentro da pasta `FolhaPagamento` após extrair o ZIP:

```text
mkdir bin
javac -encoding UTF-8 -d bin src/Colaborador.java src/ColaboradorPadrao.java src/ColaboradorComissionado.java src/ColaboradorProducao.java src/Principal.java
java -Dfile.encoding=UTF-8 -cp bin Principal
```

Se `bin` já existir, pule `mkdir bin`. No PowerShell, coloque `"-Dfile.encoding=UTF-8"` entre aspas. No Windows, use `chcp 65001` antes de executar para o terminal trabalhar com UTF-8. Se `java` ou `javac` não forem reconhecidos, configure a pasta `bin` do JDK no PATH ou execute os comandos pelo caminho completo do JDK.

Alternativamente, abra `src` em uma IDE Java e execute o método `main` da classe `Principal`.

## Funcionalidades

- Cadastrar colaborador padrão, comissionado ou por produção.
- Listar dados, adicionais e salário final de todos os cadastrados.
- Consultar um colaborador por matrícula.
- Exibir a folha completa, a quantidade de colaboradores e o total a pagar.
- Encerrar pelo menu com a opção 0.

As opções do objeto interativo não foram incluídas no enunciado recebido. O menu acima foi adotado para cobrir cadastro e cálculo, com listagem e consulta como complementos.

## Regras e uso

Todos recebem salário base de R$ 2.000,00, definido em `Colaborador.SALARIO_BASE`. O padrão recebe apenas a base; o comissionado recebe base + vendas × percentual / 100; o de produção recebe base + quantidade de peças × valor por peça. Não há descontos, impostos ou outras regras além das solicitadas.

Informe valores decimais com vírgula ou ponto, **sem separador de milhar**: `10000`, `350,50` ou `350.50`. Para uma comissão de 5%, digite `5`. O percentual deve ser não negativo; não foi imposto um teto de 100%, pois o enunciado não o exige.

A matrícula é um inteiro positivo, único na lista; zeros à esquerda não diferenciam matrículas. A quantidade de peças é inteira e não negativa. Ambos os campos aceitam até 2147483647. Nomes vazios, números negativos, texto em campos numéricos, NaN, infinito e cálculos que excedam a capacidade do tipo são rejeitados. Entradas inválidas são solicitadas novamente.

Os dados ficam em um `ArrayList<Colaborador>` apenas durante a execução. Ao sair, os cadastros são perdidos. Não há banco de dados ou persistência em arquivos nesta etapa.

## Organização e POO

- `src/Colaborador.java`: superclasse abstrata com matrícula, nome e salário base privados; constante, getters e métodos comuns.
- `src/ColaboradorPadrao.java`: subclasse sem atributos extras.
- `src/ColaboradorComissionado.java`: vendas, percentual e cálculo da comissão.
- `src/ColaboradorProducao.java`: quantidade, valor por peça e cálculo da produtividade.
- `src/Principal.java`: Scanner, menu com do/while e switch, validação e ArrayList.
- `FLUXOGRAMA.md`: planejamento em Mermaid, visualizável em editores compatíveis.
- `testes/TesteFolha.java`: testes de cálculo, polimorfismo e validação, sem bibliotecas externas.
- `VALIDACAO.md`: resultados da verificação do projeto.

Os objetos são criados com `new`. Herança usa `extends`; sobrescrita usa `@Override`. O menu trabalha com referências da superclasse, e cada subclasse executa seu próprio cálculo (polimorfismo). Os atributos privados e os getters aplicam encapsulamento. Os dados são definidos no construtor e não possuem setters, pois esta etapa não oferece edição de cadastros.

Os cálculos individuais usam `double` para manter o foco didático. A apresentação arredonda para duas casas com HALF_EVEN; o total usa `BigDecimal` para somar exatamente os salários já arredondados em centavos. Não se trata de uma folha fiscal para uso empresarial.

## Exemplo para conferir

1. Cadastre Ana Silva como padrão: salário final R$ 2.000,00.
2. Cadastre Bruno Souza como comissionado, vendas de 10000 e comissão de 5: comissão de R$ 500,00 e salário de R$ 2.500,00.
3. Cadastre Carla Santos por produção, 100 peças a 3,50: produtividade de R$ 350,00 e salário de R$ 2.350,00.
4. Selecione a opção 4: 3 colaboradores e total de R$ 6.850,00.

## Executar os testes

Após compilar o projeto:

```text
javac -encoding UTF-8 -cp bin -d bin testes/TesteFolha.java
java -Dfile.encoding=UTF-8 -cp bin TesteFolha
```

Resultado esperado: `OK: 23 verificações concluídas.`
