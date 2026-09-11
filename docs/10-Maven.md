# Maven

## Índice
- [O que é Maven](#o-que-é)
- [Estrutura Padrão](#estrutura-padrão-de-um-projeto-maven)
- [Anatomia](#anatomia-do-pomxml)
- [Ciclo de Vida](#build-lifecycle-ciclo-de-vida-do-build)
- [Comandos](#comandos-essenciais)
- [Adicionando Dependência](#adicionando-uma-dependência)

## O que é

Maven é uma ferramenta de **gerenciamento de dependências e automação de build** para projetos Java. Além de baixar bibliotecas, também compila o código, roda testes, empacota o projeto (`.jar`/`.war`) e pode publicá-lo em repositórios.

Diferente de gerenciadores como Poetry/uv (Python), que só cuidam de dependências e ambiente, o Maven cobre todo o ciclo de vida do build, já que Java é uma linguagem compilada.

## Estrutura padrão de um projeto Maven

```
meu-projeto/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/        (código-fonte)
│   │   └── resources/    (arquivos de configuração, properties, etc.)
│   └── test/
│       └── java/         (testes)
└── target/                (gerado pelo Maven: .class, .jar, etc.)
```

Essa estrutura é uma convenção seguida por praticamente todo projeto Java (inclusive os gerados pelo Spring Initializr), o que facilita a leitura de projetos de terceiros.

## Anatomia do pom.xml

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.exemplo</groupId>
    <artifactId>meu-projeto</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.7.3</version>
        </dependency>
    </dependencies>
</project>
```

- `groupId`: identifica o "dono" do projeto (geralmente domínio invertido, ex.: `com.exemplo`)
- `artifactId`: nome do projeto/módulo
- `version`: versão do projeto (o sufixo `-SNAPSHOT` indica versão em desenvolvimento)
- `dependencies`: lista de bibliotecas externas usadas

## Build lifecycle (ciclo de vida do build)

O Maven organiza o processo de build em fases sequenciais. Rodar uma fase executa automaticamente todas as anteriores:

| Fase | O que faz |
|---|---|
| `validate` | Verifica se a estrutura do projeto está correta |
| `compile` | Compila o código-fonte (`src/main/java`) |
| `test` | Roda os testes (`src/test/java`) |
| `package` | Empacota o código compilado em `.jar` ou `.war` |
| `verify` | Roda checagens sobre o pacote gerado |
| `install` | Instala o pacote no repositório local (`~/.m2/repository`), disponível para outros projetos locais |
| `deploy` | Publica o pacote em um repositório remoto |

## Comandos essenciais

```bash
mvn compile          # compila o código
mvn test              # roda os testes
mvn package            # gera o .jar em target/
mvn install             # instala no repositório local (~/.m2)
mvn clean               # remove a pasta target/
mvn clean install        # combinação comum: limpa e reconstrói tudo
mvn dependency:tree       # mostra a árvore de dependências do projeto
```

## Adicionando uma dependência

1. Localizar a dependência no [Maven Central](https://mvnrepository.com/)
2. Copiar o bloco `<dependency>` fornecido pelo site
3. Colar dentro da tag `<dependencies>` do `pom.xml`
4. Rodar `mvn compile` (ou deixar a extensão do VS Code baixar automaticamente ao salvar o arquivo)

Exemplo de dependência do driver JDBC do PostgreSQL:

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>42.7.3</version>
</dependency>
```