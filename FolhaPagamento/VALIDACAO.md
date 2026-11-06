# Verificação do projeto

Verificado em 06/11/2026.

## Compilação

Compilação bem-sucedida com JDK 27 e `javac --release 8 -encoding UTF-8`, incluindo os cinco arquivos do programa e a classe de testes. O compilador emitiu apenas avisos de que o alvo Java 8 é antigo. A execução ocorreu no JDK 27; não foi executado um runtime Java 8 separado.

## Testes automatizados

`TesteFolha` passou em 23 verificações: os três salários, comissão, produtividade, adicionais zero, percentual decimal, soma por polimorfismo e rejeição de matrícula inválida, nome vazio/nulo, valores negativos, NaN, infinito e resultados numéricos excessivos.

## Teste integrado do terminal

Foi executada uma sessão com entradas simuladas para:

- Listar, consultar e calcular sem cadastros.
- Rejeitar texto e opções fora do menu.
- Rejeitar matrícula negativa, duplicada e nome vazio.
- Rejeitar vendas e percentuais negativos, texto, NaN e infinito.
- Rejeitar quantidade de peças negativa ou fracionária e valor por peça negativo.
- Cadastrar exatamente um colaborador de cada tipo.
- Aceitar vírgula decimal no valor por peça.
- Listar os cadastros, consultar matrícula existente e informar matrícula não encontrada.
- Conferir salários de R$ 2.000,00, R$ 2.500,00 e R$ 2.350,00, com total de R$ 6.850,00.
- Sair pelo menu e encerrar de forma controlada quando a entrada termina durante o cadastro.

Todos esses cenários passaram. Na captura de saída pelo PowerShell, foi usado `-Dstdout.encoding=UTF-8` junto de `-Dfile.encoding=UTF-8` para preservar os acentos do JDK 27. Os testes de domínio estão incluídos na pasta `testes`.
