# Address App

AddressApp é uma aplicação didática a ser usada no primeiro bimestre letivo durante as aulas de LP2 (Linguagens de Programação 2).

Esse repositório conterá o desenvolvimento da atividade realizado por:

- Daniel H. Lelis
- Ana Luisa

## Requisitos

- Java 14+
  - Essa é a versão alvo do Java, mas está sendo evitado de se usar recursos anteriores ao Java 9
- Netbeans 11+
  - IntelliJ também funcionará
- PostgreSQL 12

## Build & Run

Para compilar e executar o código, basta utilizar a ferramenta já integrada à IDE utilizada. Importante verificar (principalmente no IntelliJ) se o SDK/JDK e a `Language Level` estão na versão certa.

### SQL

**Importante:** antes de rodar a aplicação pela primeira vez, será necessário criar o banco de dados baseando-se no script SQL presente em `sql`.

### Variáveis de ambiente

Os seguintes parâmetros do DBMS (_Sistema Gerenciador de Bancos de Dados_) são modificáveis por variáveis de ambiente:

- `DB_HOST`: Host do DBMS (default: localhost)
- `DB_PORT`: Porta do DBMS (default: 5432)
- `DB_DB`: Bancos de dados do DBMS (default: address)
- `DB_USER`: Usuário do DBMS (default: postgres)
- `DB_PASS`: Senha do DBMS (default: postgres)

#### Netbeans

No Netbeans, para modificar as variáveis de ambiente, recomenda-se executá-lo pela linha de comando.

#### IntelliJ

No IntelliJ, para modificar as variáveis de ambiente, recomenda-se modificar as configurações de `Run & Build` do projeto.

## Dependências

- JDBC do PostgreSQL (já presente no repo)

## Relatórios

Os relatórios se situam na pasta `docs`, e estão em markdown.