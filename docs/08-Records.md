# Records

## Índice

- [O que é um Record](#o-que-é-um-record)
- [Record x Classe Tradicional](#record-x-classe-tradicional)
- [Métodos Gerados Automaticamente](#métodos-gerados-automaticamente)
- [Validação no Construtor Compacto](#validação-no-construtor-compacto)
- [Record com Métodos Próprios](#record-com-métodos-próprios)
- [Record Implementando Interface](#record-implementando-interface)
- [Limitações](#limitações)
- [Comparando com Python](#comparando-com-python)

---

## O que é um Record

Um Record é um tipo especial de classe, introduzido no Java 16, destinado a representar dados imutáveis de forma concisa. Um Record com nome e atributos declarados no cabeçalho já vem automaticamente com construtor, getters, `equals()`, `hashCode()` e `toString()`, sem necessidade de escrever nenhum deles manualmente.

```java
public record Anime(String nome, int ano, double nota) {
}
```

```java
Anime anime = new Anime("Naruto", 2002, 8.5);

System.out.println(anime.nome());  // "Naruto"
System.out.println(anime.ano());   // 2002
System.out.println(anime.nota());  // 8.5
System.out.println(anime);         // Anime[nome=Naruto, ano=2002, nota=8.5]
```

Os atributos declarados entre parênteses no cabeçalho são chamados de **componentes** do Record. Cada componente vira automaticamente um atributo `private final`, com um método de acesso de mesmo nome (sem prefixo `get`).

## Record x Classe Tradicional

O mesmo `Anime`, escrito como classe tradicional, exige bem mais código para o mesmo resultado.

```java
public final class Anime {
    private final String nome;
    private final int ano;
    private final double nota;

    public Anime(String nome, int ano, double nota) {
        this.nome = nome;
        this.ano = ano;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public int getAno() {
        return ano;
    }

    public double getNota() {
        return nota;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Anime)) return false;
        Anime outro = (Anime) obj;
        return ano == outro.ano
                && Double.compare(nota, outro.nota) == 0
                && Objects.equals(nome, outro.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, ano, nota);
    }

    @Override
    public String toString() {
        return "Anime[nome=" + nome + ", ano=" + ano + ", nota=" + nota + "]";
    }
}
```

Todo esse código, construtor, getters, `equals()`, `hashCode()` e `toString()`, é gerado automaticamente pela declaração de uma linha do Record.

## Métodos Gerados Automaticamente

| Método | Comportamento |
| ------ | -------------- |
| Construtor | Recebe todos os componentes, na ordem declarada |
| Métodos de acesso | Um por componente, mesmo nome, sem prefixo `get` |
| `equals()` | Compara todos os componentes |
| `hashCode()` | Baseado em todos os componentes |
| `toString()` | Formato `NomeDoRecord[componente1=valor1, componente2=valor2, ...]` |

```java
Anime a1 = new Anime("Naruto", 2002, 8.5);
Anime a2 = new Anime("Naruto", 2002, 8.5);

System.out.println(a1.equals(a2)); // true, compara todos os componentes
System.out.println(a1 == a2);      // false, objetos diferentes na memória
```

Não existem setters. Todos os componentes são `final`, um Record é imutável por padrão. Para "alterar" um valor, é necessário criar uma nova instância.

```java
Anime original = new Anime("Naruto", 2002, 8.5);
Anime atualizado = new Anime(original.nome(), original.ano(), 9.0); // nova instância, nota diferente
```

## Validação no Construtor Compacto

É possível adicionar validação sem reescrever a lista de parâmetros, usando o **construtor compacto**, uma forma reduzida onde os parâmetros já são implícitos.

```java
public record Anime(String nome, int ano, double nota) {
    public Anime { // construtor compacto, sem parênteses e parâmetros repetidos
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("Nota deve estar entre 0 e 10");
        }
    }
}
```

```java
Anime valido = new Anime("Naruto", 2002, 8.5);      // funciona normalmente
Anime invalido = new Anime("Naruto", 2002, 15.0);   // IllegalArgumentException
```

Dentro do construtor compacto, a atribuição dos componentes ao objeto acontece automaticamente ao final, de forma implícita, não é necessário (nem permitido) escrever `this.nome = nome` manualmente.

É possível também normalizar valores dentro do construtor compacto, reatribuindo o próprio parâmetro antes da atribuição implícita.

```java
public record Anime(String nome, int ano, double nota) {
    public Anime {
        nome = nome.trim(); // normaliza o valor antes da atribuição automática
    }
}
```

## Record com Métodos Próprios

Além dos métodos gerados automaticamente, um Record pode ter métodos adicionais, de instância ou estáticos, e até métodos de acesso sobrescritos manualmente.

```java
public record Anime(String nome, int ano, double nota) {
    public boolean ehClassico() {
        return ano < 2010;
    }

    public static Anime criarComNotaZero(String nome, int ano) {
        return new Anime(nome, ano, 0.0);
    }
}
```

```java
Anime anime = new Anime("Naruto", 2002, 8.5);
System.out.println(anime.ehClassico()); // true

Anime semNota = Anime.criarComNotaZero("Bleach", 2004);
```

Um método de acesso também pode ser sobrescrito, substituindo o comportamento padrão gerado automaticamente.

```java
public record Anime(String nome, int ano, double nota) {
    @Override
    public String nome() { // sobrescreve o getter padrão
        return nome.toUpperCase();
    }
}
```

## Record Implementando Interface

Um Record não pode herdar de outra classe (assim como um Enum, um Record já estende implicitamente `java.lang.Record`), mas pode implementar interfaces normalmente.

```java
interface Avaliavel {
    boolean ehBemAvaliado();
}

public record Anime(String nome, int ano, double nota) implements Avaliavel {
    @Override
    public boolean ehBemAvaliado() {
        return nota >= 8.0;
    }
}
```

```java
Avaliavel avaliavel = new Anime("Naruto", 2002, 8.5);
System.out.println(avaliavel.ehBemAvaliado()); // true
```

## Limitações

Um Record não pode:

- Herdar de outra classe, apenas implementar interfaces.
- Declarar atributos de instância adicionais fora dos componentes do cabeçalho.
- Ter componentes mutáveis, todo componente é implicitamente `final`.
- Ser estendido por outra classe, um Record é implicitamente `final`.

```java
public record Anime(String nome, int ano, double nota) {
    private String genero; // erro de compilação, atributo adicional não permitido
}
```

Record é indicado para representar dados que não mudam depois de criados, DTOs (objetos de transferência de dados), respostas de API, chaves compostas, entre outros casos onde imutabilidade e concisão são desejadas. Para classes com estado mutável ou lógica de negócio complexa, uma classe tradicional continua sendo a escolha adequada.

## Comparando com Python

| Python | Java |
| ------ | ---- |
| `@dataclass` | `record` |
| `@dataclass(frozen=True)` | `record` (já imutável por padrão) |
| Gerado automaticamente: `__init__`, `__repr__`, `__eq__` | Gerado automaticamente: construtor, `toString()`, `equals()`, `hashCode()` |
| `NamedTuple` | `record` (propósito parecido, dados imutáveis nomeados) |
| Validação em `__post_init__` | Validação no construtor compacto |

```python
from dataclasses import dataclass

@dataclass(frozen=True)
class Anime:
    nome: str
    ano: int
    nota: float
```

```java
public record Anime(String nome, int ano, double nota) {
}
```

A comparação mais próxima é com `@dataclass(frozen=True)` do Python, ambos resolvem o mesmo problema, evitar código repetitivo (boilerplate) para classes que só carregam dados. A diferença de sempre se repete, Python permite contornar a imutabilidade com mais facilidade caso necessário, enquanto Record reforça a imutabilidade de forma mais rígida, sem uma forma direta de "burlar" isso de dentro da própria linguagem.