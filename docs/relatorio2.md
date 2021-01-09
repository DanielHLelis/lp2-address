---
author: ["Daniel H. Lelis", "Ana Luisa"]
date: "07/01/2021"
---


# Modificações

- Transferência para o Gradle
- Adição de testes

## Gradle

O projeto agora está sendo gerenciado pelo Gradle, dessa forma a adição de
dependências (como os _connectors_ e o JUnit) fica facilitada, o processo de
compilação, execução, testagem e outras tarefas agora são mais fáceis de serem
criadas e executadas, não sendo mais acopladas à IDEs.

## Testes

Houve a implementação de testes por meio do JUnit 5. Tais testes podem ser
executados pelo Gradle usando o comando: `gradlew test`.

## Estrutura do projeto

O projeto sofreu duas mudanças estruturais:

### Padronização estrutural

A primeira foi uma adequação ao modelo de diretórios estabelecidos pela
documentação do Maven (e do Gradle), separando a `src` em um diretório de
aplicação (`main`) e outro de testes (`test`). Além disso, esses diretórios
apresentam divisões para código-fonte (`java`), recursos (`resources`) e outros.

[Maven's standard directory layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)  
[Gradle project directory layout](https://docs.gradle.org/current/userguide/directory_layout.html#dir:project_root)

### Implementação do modelo MVC (ou quase isso)

_A fazer_