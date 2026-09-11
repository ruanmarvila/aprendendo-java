# Leitura e Escrita de Arquivos (I/O)

## Índice

- [Path e Files](#path-e-files)
- [Manipulando Path](#manipulando-path)
- [Atributos de Arquivo](#atributos-de-arquivo)
- [Ler um Arquivo Inteiro](#ler-um-arquivo-inteiro)
- [Ler Linha por Linha](#ler-linha-por-linha)
- [Escrever em um Arquivo](#escrever-em-um-arquivo)
  - [Sobrescrevendo](#sobrescrevendo)
  - [Adicionando ao Final (Append)](#adicionando-ao-final-append)
- [try-with-resources](#try-with-resources)
- [Verificando se um Arquivo Existe](#verificando-se-um-arquivo-existe)
- [Listando Arquivos de uma Pasta](#listando-arquivos-de-uma-pasta)
  - [DirectoryStream](#directorystream)
  - [PathMatcher](#pathmatcher)
- [Percorrendo uma Árvore de Pastas](#percorrendo-uma-árvore-de-pastas)
  - [SimpleFileVisitor](#simplefilevisitor)
- [Compactando Arquivos (Zip)](#compactando-arquivos-zip)
  - [ZipOutputStream](#zipoutputstream)
- [Lendo e Escrevendo CSV Manualmente](#lendo-e-escrevendo-csv-manualmente)
- [Comparando com Python](#comparando-com-python)

## Path e Files

`Path` representa um caminho no sistema de arquivos, um arquivo ou uma pasta, sem necessariamente existir de fato ainda. `Files` é uma classe utilitária com métodos estáticos que operam sobre um `Path`, para ler, escrever, verificar existência, entre outras operações.

```java
import java.nio.file.Path;
import java.nio.file.Files;

Path caminho = Path.of("dados.txt");
```

`Path.of(...)` aceita caminho relativo (à pasta onde o programa é executado) ou absoluto.

```java
Path relativo = Path.of("dados.txt");
Path absoluto = Path.of("C:/Users/nome/Documents/dados.txt");
Path comSubpastas = Path.of("dados", "usuarios", "lista.txt"); // dados/usuarios/lista.txt
```

## Manipulando Path

Além de representar um caminho, `Path` oferece métodos para combinar, simplificar e comparar caminhos, sem tocar no sistema de arquivos.

### normalize

Remove redundâncias de um caminho, como `.` (diretório atual) e `..` (diretório anterior), sem verificar se o caminho existe de fato.

```java
Path caminho = Path.of("pasta/../pasta/./arquivo.txt");
Path normalizado = caminho.normalize();

System.out.println(normalizado); // pasta/arquivo.txt
```

### resolve

Combina um `Path` base com outro caminho, geralmente relativo, formando um novo caminho completo.

```java
Path base = Path.of("dados");
Path completo = base.resolve("usuarios/arquivo.txt");

System.out.println(completo); // dados/usuarios/arquivo.txt
```

Se o caminho passado a `resolve` já for absoluto, o resultado ignora a base e retorna apenas o caminho absoluto.

```java
Path resultado = base.resolve("/etc/config.txt");
System.out.println(resultado); // /etc/config.txt, ignora "dados"
```

### relativize

Calcula o caminho relativo necessário para ir de um `Path` até outro.

```java
Path origem = Path.of("dados/usuarios");
Path destino = Path.of("dados/usuarios/joao/arquivo.txt");

Path relativo = origem.relativize(destino);

System.out.println(relativo); // joao/arquivo.txt
```

### Outros métodos úteis

| Método | Função |
| ------ | ------ |
| `getFileName()` | Retorna só o nome do arquivo, sem o restante do caminho |
| `getParent()` | Retorna o caminho da pasta pai |
| `toAbsolutePath()` | Converte um caminho relativo em absoluto |
| `isAbsolute()` | Verifica se o caminho já é absoluto |
| `startsWith(outro)` / `endsWith(outro)` | Verifica se o caminho começa ou termina com outro caminho |

```java
Path caminho = Path.of("dados/usuarios/arquivo.txt");

System.out.println(caminho.getFileName()); // arquivo.txt
System.out.println(caminho.getParent());   // dados/usuarios
```

## Atributos de Arquivo

`BasicFileAttributes` reúne metadados de um arquivo ou pasta, obtidos através de `Files.readAttributes`.

```java
import java.nio.file.attribute.BasicFileAttributes;

Path caminho = Path.of("dados.txt");
BasicFileAttributes atributos = Files.readAttributes(caminho, BasicFileAttributes.class);

System.out.println(atributos.creationTime());     // data de criação
System.out.println(atributos.lastModifiedTime());  // última modificação
System.out.println(atributos.size());               // tamanho em bytes
System.out.println(atributos.isDirectory());         // se é uma pasta
System.out.println(atributos.isRegularFile());       // se é um arquivo comum
```

Atributos individuais também podem ser lidos ou alterados diretamente, sem passar por `BasicFileAttributes`.

```java
FileTime ultimaModificacao = Files.getLastModifiedTime(caminho);
Files.setLastModifiedTime(caminho, FileTime.fromMillis(System.currentTimeMillis()));

boolean oculto = Files.isHidden(caminho);
boolean legivel = Files.isReadable(caminho);
boolean gravavel = Files.isWritable(caminho);
```

## Ler um Arquivo Inteiro

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

Path caminho = Path.of("dados.txt");

try {
    String conteudo = Files.readString(caminho);
    System.out.println(conteudo);
} catch (IOException e) {
    System.out.println("Erro ao ler o arquivo: " + e.getMessage());
}
```

`Files.readString` lê o conteúdo inteiro do arquivo em uma única `String`, incluindo quebras de linha. `IOException` é uma checked exception, então o tratamento com `try/catch` (ou `throws` no método) é obrigatório.

## Ler Linha por Linha

```java
List<String> linhas = Files.readAllLines(caminho);

for (String linha : linhas) {
    System.out.println(linha);
}
```

`Files.readAllLines` retorna uma `List<String>`, cada elemento representando uma linha do arquivo, sem o caractere de quebra de linha.

Para arquivos grandes, onde carregar tudo em memória de uma vez não é desejável, `Files.lines` retorna uma `Stream<String>`, processada sob demanda.

```java
try (Stream<String> linhas = Files.lines(caminho)) {
    linhas.filter(linha -> !linha.isBlank())
          .forEach(System.out::println);
}
```

## Escrever em um Arquivo

### Sobrescrevendo

```java
String conteudo = "Primeira linha\nSegunda linha";

Files.writeString(caminho, conteudo);
```

Por padrão, `Files.writeString` cria o arquivo se não existir, e sobrescreve completamente o conteúdo se já existir.

### Adicionando ao Final (Append)

```java
import java.nio.file.StandardOpenOption;

Files.writeString(caminho, "Nova linha\n", StandardOpenOption.APPEND);
```

`StandardOpenOption.APPEND` altera o comportamento padrão, adicionando o conteúdo ao final do arquivo, em vez de sobrescrever. Se o arquivo não existir ainda, é necessário combinar com `StandardOpenOption.CREATE`.

```java
Files.writeString(
    caminho,
    "Nova linha\n",
    StandardOpenOption.CREATE,
    StandardOpenOption.APPEND
);
```

## try-with-resources

Alguns recursos de I/O (como `BufferedReader`, `BufferedWriter`, `Scanner`) implementam a interface `AutoCloseable`, e precisam ser fechados manualmente após o uso, para liberar o recurso do sistema operacional.

```java
BufferedReader leitor = Files.newBufferedReader("dados.txt");

try {
    String linha;
    while ((linha = leitor.readLine()) != null) {
        System.out.println(linha);
    }
} finally {
    leitor.close(); // fechamento manual, precisa ser lembrado
}
```

`try-with-resources` é uma forma de declarar o recurso diretamente no `try`, entre parênteses, garantindo o fechamento automático ao final do bloco, mesmo que uma exceção ocorra.

```java
try (BufferedReader leitor = Files.newBufferedReader("dados.txt");) {
    String linha;
    while ((linha = leitor.readLine()) != null) {
        System.out.println(linha);
    }
} catch (IOException e) {
    System.out.println("Erro ao ler: " + e.getMessage());
}
```

É possível declarar múltiplos recursos no mesmo `try`, separados por ponto e vírgula, fechados na ordem inversa da declaração.

```java
try (
    BufferedReader leitor = Files.newBufferedReader("dados.txt");
    BufferedWriter escritor = Files.newBufferedWriter("destino.txt");
) {
    String linha;
    while ((linha = leitor.readLine()) != null) {
        escritor.write(linha);
        escritor.newLine();
    }
} catch (IOException e) {
    System.out.println("Erro: " + e.getMessage());
}
```

`Files.lines`, usada anteriormente com `Stream<String>`, também precisa de `try-with-resources`, já que `Stream` retornado por essa operação implementa `AutoCloseable`, mantendo um arquivo aberto por trás dos panos enquanto a Stream não é totalmente consumida ou fechada.

## Verificando se um Arquivo Existe

```java
Path caminho = Path.of("dados.txt");

if (Files.exists(caminho)) {
    System.out.println("Arquivo existe");
} else {
    System.out.println("Arquivo não encontrado");
}
```

| Método | Função |
| ------ | ------ |
| `Files.exists(caminho)` | Verifica se o arquivo ou pasta existe |
| `Files.notExists(caminho)` | Verifica se não existe |
| `Files.isDirectory(caminho)` | Verifica se é uma pasta |
| `Files.isRegularFile(caminho)` | Verifica se é um arquivo comum |
| `Files.deleteIfExists(caminho)` | Remove o arquivo, se existir, sem lançar erro caso não exista |
| `Files.createFile(caminho)` | Cria um arquivo vazio, lança exceção se já existir |
| `Files.createDirectories(caminho)` | Cria a pasta (e pastas intermediárias necessárias), sem erro se já existir |

## Listando Arquivos de uma Pasta

### DirectoryStream

Itera sobre o conteúdo de uma pasta, arquivos e subpastas diretamente dentro dela, sem entrar recursivamente nas subpastas.

```java
try (DirectoryStream<Path> stream = Files.newDirectoryStream(Path.of("dados"))) {
    for (Path item : stream) {
        System.out.println(item);
    }
} catch (IOException e) {
    System.out.println("Erro ao listar: " + e.getMessage());
}
```

`DirectoryStream` implementa `AutoCloseable`, exigindo `try-with-resources`.

Uma alternativa mais moderna, sem necessidade de `try-with-resources` explícito quando combinada com Streams do jeito documentado anteriormente, é `Files.list`, que retorna diretamente uma `Stream<Path>`.

```java
try (Stream<Path> stream = Files.list(Path.of("dados"))) {
    stream.forEach(System.out::println);
}
```

### PathMatcher

Define um padrão para filtrar caminhos, usando sintaxe glob (`*.txt`, `**/*.java`) ou regex.

```java
PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.txt");

try (DirectoryStream<Path> stream = Files.newDirectoryStream(Path.of("dados"))) {
    for (Path item : stream) {
        if (matcher.matches(item.getFileName())) {
            System.out.println(item);
        }
    }
}
```

`DirectoryStream` também aceita um padrão glob diretamente como segundo argumento, dispensando `PathMatcher` separado para o caso simples.

```java
try (DirectoryStream<Path> stream = Files.newDirectoryStream(Path.of("dados"), "*.txt")) {
    for (Path item : stream) {
        System.out.println(item); // já filtrado, só .txt
    }
}
```

`PathMatcher` isolado é útil quando o filtro precisa ser aplicado em outro contexto, como dentro de `SimpleFileVisitor`, a seguir.

## Percorrendo uma Árvore de Pastas

### SimpleFileVisitor

Usado com `Files.walkFileTree`, percorre recursivamente uma árvore de pastas inteira, entrando em todas as subpastas. `SimpleFileVisitor` oferece métodos que podem ser sobrescritos para agir em cada etapa da visita.

```java
Files.walkFileTree(Path.of("dados"), new SimpleFileVisitor<Path>() {
    @Override
    public FileVisitResult visitFile(Path arquivo, BasicFileAttributes attrs) {
        System.out.println("Arquivo: " + arquivo);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path pasta, BasicFileAttributes attrs) {
        System.out.println("Entrando na pasta: " + pasta);
        return FileVisitResult.CONTINUE;
    }
});
```

| Método sobrescrevível | Quando é chamado |
| ---------------------- | ------------------ |
| `preVisitDirectory` | Antes de entrar em uma pasta |
| `visitFile` | Para cada arquivo encontrado |
| `postVisitDirectory` | Depois de terminar de visitar uma pasta |
| `visitFileFailed` | Quando um arquivo não pode ser acessado |

Cada método retorna um `FileVisitResult`, controlando a continuidade da visita.

| Retorno | Efeito |
| ------- | ------ |
| `CONTINUE` | Continua a visita normalmente |
| `SKIP_SUBTREE` | Pula todas as subpastas da pasta atual |
| `SKIP_SIBLINGS` | Pula os itens irmãos restantes no mesmo nível |
| `TERMINATE` | Encerra a visita imediatamente |

Uma alternativa mais moderna e concisa, para casos onde não é necessário o controle detalhado de `SimpleFileVisitor`, é `Files.walk`, retornando uma `Stream<Path>` de toda a árvore.

```java
try (Stream<Path> caminhos = Files.walk(Path.of("dados"))) {
    caminhos.filter(Files::isRegularFile)
            .forEach(System.out::println);
}
```

## Compactando Arquivos (Zip)

### ZipOutputStream

Do pacote `java.util.zip`, fora do NIO propriamente dito, `ZipOutputStream` permite compactar um ou mais arquivos em um único `.zip`.

```java
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("saida.zip"))) {
    zip.putNextEntry(new ZipEntry("arquivo.txt"));
    zip.write(Files.readAllBytes(Path.of("arquivo.txt")));
    zip.closeEntry();
}
```

`putNextEntry` inicia uma nova entrada dentro do zip, `write` grava o conteúdo dessa entrada, `closeEntry` finaliza a entrada atual, antes de iniciar a próxima (se houver).

**Compactando múltiplos arquivos:**

```java
List<Path> arquivos = List.of(Path.of("um.txt"), Path.of("dois.txt"));

try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("saida.zip"))) {
    for (Path arquivo : arquivos) {
        zip.putNextEntry(new ZipEntry(arquivo.getFileName().toString()));
        zip.write(Files.readAllBytes(arquivo));
        zip.closeEntry();
    }
}
```

**Descompactando**, usando `ZipInputStream`, o inverso de `ZipOutputStream`:

```java
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

try (ZipInputStream zipEntrada = new ZipInputStream(new FileInputStream("saida.zip"))) {
    ZipEntry entrada;

    while ((entrada = zipEntrada.getNextEntry()) != null) {
        Path destino = Path.of("descompactado", entrada.getName());
        Files.createDirectories(destino.getParent());
        Files.copy(zipEntrada, destino);
    }
}
```

## Lendo e Escrevendo CSV Manualmente

Sem uma biblioteca externa, um arquivo CSV simples pode ser lido e escrito manualmente, usando `split` para separar colunas e `join` (ou concatenação) para montar linhas.

```java
public record Aluno(String nome, int idade, double nota) {
}
```

**Escrevendo uma lista de objetos em CSV:**

```java
List<Aluno> alunos = List.of(
    new Aluno("Ana", 20, 8.5),
    new Aluno("Bruno", 22, 7.0)
);

List<String> linhas = new ArrayList<>();
linhas.add("nome,idade,nota"); // cabeçalho

for (Aluno aluno : alunos) {
    linhas.add(aluno.nome() + "," + aluno.idade() + "," + aluno.nota());
}

Files.write(Path.of("alunos.csv"), linhas);
```

**Lendo um CSV de volta para objetos:**

```java
List<String> linhasArquivo = Files.readAllLines(Path.of("alunos.csv"));

List<Aluno> alunosLidos = new ArrayList<>();

for (int i = 1; i < linhasArquivo.size(); i++) { // pula o cabeçalho, índice 0
    String[] campos = linhasArquivo.get(i).split(",");

    String nome = campos[0];
    int idade = Integer.parseInt(campos[1]);
    double nota = Double.parseDouble(campos[2]);

    alunosLidos.add(new Aluno(nome, idade, nota));
}

alunosLidos.forEach(System.out::println);
```

Essa abordagem manual funciona bem para casos simples, sem campos contendo vírgula dentro do próprio valor. Para CSVs mais complexos (valores com vírgula, aspas, quebras de linha dentro de um campo), uma biblioteca dedicada (como Apache Commons CSV ou OpenCSV) resolve esses casos de forma mais robusta, tratamento que a implementação manual não cobre.

## Comparando com Python

| Python | Java |
| ------ | ---- |
| `open(arquivo).read()` | `Files.readString(caminho)` |
| `open(arquivo).readlines()` | `Files.readAllLines(caminho)` |
| `open(arquivo, "w").write(texto)` | `Files.writeString(caminho, texto)` |
| `open(arquivo, "a").write(texto)` | `Files.writeString(caminho, texto, StandardOpenOption.APPEND)` |
| `with open(arquivo) as f:` | `try (recurso = ...) { }`, try-with-resources |
| `os.path.exists(arquivo)` | `Files.exists(caminho)` |
| `csv.reader`/`csv.writer` | Sem equivalente na biblioteca padrão, requer split manual ou lib externa |

O `with` do Python e o `try-with-resources` do Java resolvem o mesmo problema, garantir o fechamento do recurso mesmo em caso de erro, com sintaxes diferentes mas propósito idêntico. A diferença mais notável fica por conta do CSV, Python já traz `csv` pronto na biblioteca padrão, enquanto Java exige implementação manual ou dependência externa para o mesmo resultado, reforçando o padrão de filosofia de bibliotecas já visto em outros tópicos.