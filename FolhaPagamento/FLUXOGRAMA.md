# Planejamento do sistema

Fluxogramas preparados antes da implementação. Os blocos abaixo usam Mermaid.

## Menu principal

```mermaid
flowchart TD
    A([Início]) --> B[Criar Scanner e ArrayList de Colaborador]
    B --> C[Exibir menu e ler opção]
    C --> D{Opção}
    D -->|1| E[Cadastrar colaborador]
    D -->|2| F[Listar colaboradores e salários]
    D -->|3| G[Buscar por matrícula]
    D -->|4| H[Calcular folha e total]
    D -->|0| I[Fechar Scanner]
    D -->|Inválida| J[Informar erro]
    E --> C
    F --> C
    G --> C
    H --> C
    J --> C
    I --> K([Fim])
```

## Cadastro e validação

```mermaid
flowchart TD
    A([Cadastrar]) --> B[Ler tipo, matrícula e nome]
    B --> C{Dados válidos e matrícula livre?}
    C -->|Não| B
    C -->|Sim| D{Tipo}
    D -->|Padrão| E[Criar ColaboradorPadrao]
    D -->|Comissionado| F[Ler vendas e percentual]
    F --> G{Números não negativos e cálculo finito?}
    G -->|Não| F
    G -->|Sim| H[Criar ColaboradorComissionado]
    D -->|Produção| I[Ler quantidade inteira e valor por peça]
    I --> J{Números não negativos e cálculo finito?}
    J -->|Não| I
    J -->|Sim| K[Criar ColaboradorProducao]
    E --> L[Adicionar ao ArrayList]
    H --> L
    K --> L
    L --> M([Voltar ao menu])
```

## Cálculo por tipo

```mermaid
flowchart TD
    A([Calcular salário final]) --> B{Tipo do objeto}
    B -->|Padrão| C[Salário = 2000]
    B -->|Comissionado| D[Comissão = vendas × percentual ÷ 100]
    B -->|Produção| E[Produtividade = peças × valor por peça]
    D --> F[Salário = 2000 + comissão]
    E --> G[Salário = 2000 + produtividade]
    C --> H[Retornar salário]
    F --> H
    G --> H
```

O programa repete cada campo inválido. Fim da entrada encerra o programa de forma controlada. A listagem e a folha chamam os métodos dos objetos por meio de referências do tipo Colaborador, aplicando polimorfismo.
