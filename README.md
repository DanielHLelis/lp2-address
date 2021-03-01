# Address App

AddressApp é uma aplicação didática a ser usada no primeiro bimestre letivo durante as aulas de LP2 (Linguagens de Programação 2).

Esse repositório conterá o desenvolvimento da atividade realizada por:

- Daniel H. Lelis
- Ana Luisa

## Requisitos

- Java 14+
  - Essa é a versão alva\o do Java, mas está sendo evitado o uso recursos anteriores ao Java 9
- PostgreSQL 12

## Build & Run

Para compilar e executar o código, basta utilizar o Gradle. Segue uma lista de comandos úteis:

_Obs:_ No caso do **Windows**, substituir `./gradlew` por `.\gradlew.bat`

## Servidor

- `./gradlew server:run -q --console=plain`
  - Executa o servidor em `localhost:11337`
- `./gradlew client:test`
  - Executa os testes (pode causar alterações na indexação do BD e mudanças nos registros em alguns casos de falha)
- `./gradlew client:clean`
  - Limpar os arquivos compilados

## Cliente

- `./gradlew client:run -q --console=plain`
  - Executa a aplicação sem as saídas padrões do Gradle no console
- `./gradlew client:test`
  - Executa os testes
- `./gradlew client:clean`
  - Limpar os arquivos compilados
  
### SQL

**Importante:** antes de rodar a aplicação pela primeira vez, será necessário criar o banco de dados baseando-se no script SQL presente em `sql`.

### Variáveis de ambiente

Os seguintes parâmetros do DBMS (_Sistema Gerenciador de Bancos de Dados_) são modificáveis por variáveis de ambiente:

- `DB_HOST`: Host do DBMS (default: localhost)
- `DB_PORT`: Porta do DBMS (default: 5432)
- `DB_DB`: Bancos de dados do DBMS (default: address)
- `DB_USER`: Usuário do DBMS (default: postgres)
- `DB_PASS`: Senha do DBMS (default: postgres)

(Também é possível modificar os valores default no código, mas não é recomendável em caso de colaboração com o projeto)

## Dependências

Pode ser visto com mais detalhes no arquivo `build.gradle`

- PostgreSQL Connector
- JUnit 5

## Relatórios

Os relatórios se situam na pasta `docs`, e estão em markdown.
