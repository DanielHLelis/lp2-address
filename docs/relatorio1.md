---
author: ["Daniel H. Lelis", "Ana Luisa"]
date: "10/09/2020"
---


# Modificações

## Removidos

### `AddressApp`

A classe estava dotada de muitas responsabilidades, dentre elas:

- Gerenciamento da conexão
- Gerenciamento das operações de persistência de `Person`
- Interface da aplicação

Por conta disso, ela foi _completamente_ deletada, distribuindo-se suas responsabilidades para demais classes.

## `br.cefetmg.address.models`

Pacote de todos os modelos.

### `Model`

Classe abstrata que define uma entidade cuja única responsabilidade é armazenar dados e lógicas de negócio.

### `Person` -> `PersonModel`

Contém a única responsabilidade de representar os dados e lógicas de negócio vinculadas a entidade `person`.

Herda da classe `Model`.

## `br.cefetmg.address.repository`

Isola a responsabilidade de persistência dos modelos, seguindo um padrão semelhante ao _facade_.

### `ConnectionFactory`

Isola a responsabilidade de gerenciamento da conexão com o banco de dados. Segue o padrão factory, já que é uma simplificação do processo de criação das conexões.

### Interface `Repository`

Define as classes responsáveis pela persistência de um modelo específico.

### `RepositoryException`

Exceção designada a qualquer problema durante o processo de persistência.

### `PersonRepository`

Classe responsável pelo gerenciamento da persistência do modelo `PersonModel`.

Implementa a _interface_ `Repository`.

## `br.cefetmg.address.utils`

### `Utils`

Classe com métodos estáticos para operações genéricas de uso geral. Segue o padrão singleton (*zeroton*), já que não pode ser instanciada.

## `br.cefetmg.address.CLI`

Pacote responsável pelo abrigamento da interface gráfica do programa por meio da linha de comando. O app principal é composto por subapps adicionáveis e, esses subapps **podem** (não foi necessário, ainda) ter também seus próprios subapps.

### Interface `CLIApp`

Define uma sub-aplicação/comando de CLI.

### `MainApp`

Ponto de entrada da CLI, responsável pela chamada dos `subapps`. 

### `AppIO`

Singleton responsável pela intermediação das operações de IO da CLI.

### `br.cefetmg.address.CLI.subapps`

As classes desse pacote implementam a _interface_ `CLIApp`, logo todas gerenciam individualmente um dos comandos do menu.
