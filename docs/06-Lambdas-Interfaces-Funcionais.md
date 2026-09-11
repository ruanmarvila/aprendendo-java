# Lambdas e Interfaces Funcionais

## Índice

- [O que é uma Interface Funcional](#o-que-é-uma-interface-funcional)
- [Sintaxe de Lambda](#sintaxe-de-lambda)
- [Interfaces Funcionais Prontas do Java](#interfaces-funcionais-prontas-do-java)
  - [Function](#function)
  - [Predicate](#predicate)
  - [Consumer](#consumer)
  - [Supplier](#supplier)
  - [Resumo](#resumo)
- [Method Reference](#method-reference)
- [Optional](#optional)
  - [Criação](#criando-um-optional)
  - [Verificando Presença](#verificando-presença)
  - [Consumindo](#consumindo-o-valor-de-forma-mais-direta)
  - [Valores Padrãos](#valores-padrão)
  - [Transformando com Map](#transformando-o-valor-com-map)
  - [Uso Recomendado](#uso-recomendado)
- [Lambda em Collections](#lambda-em-collections)
- [Comparando com Python](#comparando-com-python)

---

## O que é uma Interface Funcional

Uma interface funcional é uma interface com exatamente **um único método abstrato**. Essa restrição é o que permite substituir a implementação dela por uma expressão lambda, ao invés de uma classe inteira.

```java
interface Saudacao {
    void cumprimentar(String nome);
}
```

Antes de lambda existir (Java 7 e anteriores), implementar essa interface exigia uma classe anônima:

```java
Saudacao s = new Saudacao() {
    @Override
    public void cumprimentar(String nome) {
        System.out.println("Olá, " + nome);
    }
};

s.cumprimentar("Ana"); // Olá, Ana
```

Com lambda, a mesma implementação fica reduzida a uma linha, sem repetir nome da interface, nome do método nem `@Override`:

```java
Saudacao s = nome -> System.out.println("Olá, " + nome);

s.cumprimentar("Ana"); // Olá, Ana
```

Uma interface pode ser marcada com `@FunctionalInterface`, uma anotação opcional que não altera o comportamento, mas faz o compilador validar que a interface realmente tem só um método abstrato, evitando que alguém adicione um segundo método por engano e quebre a compatibilidade com lambda.

```java
@FunctionalInterface
interface Saudacao {
    void cumprimentar(String nome);
}
```

## Sintaxe de Lambda

A forma geral de uma lambda é:

```
(parametros) -> corpo
```

```java
// Sem parâmetro
() -> System.out.println("Executando");

// Um parâmetro (parênteses opcionais nesse caso)
nome -> System.out.println("Olá, " + nome);
(nome) -> System.out.println("Olá, " + nome); // equivalente

// Múltiplos parâmetros (parênteses obrigatórios)
(a, b) -> a + b;

// Corpo com múltiplas linhas, exige chaves e return explícito
(a, b) -> {
    int soma = a + b;
    return soma;
};
```

O tipo dos parâmetros normalmente não é declarado, o compilador infere a partir do contexto (o tipo esperado pela interface funcional sendo implementada). É possível declarar explicitamente, mas raramente necessário.

```java
Comparator<String> porTamanho = (String a, String b) -> a.length() - b.length(); // tipo explícito
Comparator<String> porTamanho2 = (a, b) -> a.length() - b.length(); // tipo inferido, forma usual
```

## Interfaces Funcionais Prontas do Java

O pacote `java.util.function` traz um conjunto de interfaces funcionais genéricas, prontas para uso, evitando a necessidade de declarar uma interface própria para cada caso comum.

### Function

Recebe um valor e retorna outro, possivelmente de tipo diferente.

```java
Function<String, Integer> tamanho = texto -> texto.length();

System.out.println(tamanho.apply("Java")); // 4
```

### Predicate

Recebe um valor e retorna `boolean`, usado para representar uma condição/teste.

```java
Predicate<Integer> ehPar = numero -> numero % 2 == 0;

System.out.println(ehPar.test(4)); // true
System.out.println(ehPar.test(5)); // false
```

### Consumer

Recebe um valor e não retorna nada, usado para representar uma ação executada sobre o valor.

```java
Consumer<String> imprimir = texto -> System.out.println(texto);

imprimir.accept("Olá"); // Olá
```

### Supplier

Não recebe nada e retorna um valor, usado para representar uma fonte de dados sob demanda.

```java
Supplier<Double> numeroAleatorio = () -> Math.random();

System.out.println(numeroAleatorio.get()); // valor aleatório entre 0 e 1
```

### Resumo

| Interface | Recebe | Retorna | Método principal |
| --------- | ------ | ------- | ------------------ |
| `Function<T, R>` | `T` | `R` | `apply(T)` |
| `Predicate<T>` | `T` | `boolean` | `test(T)` |
| `Consumer<T>` | `T` | nada | `accept(T)` |
| `Supplier<T>` | nada | `T` | `get()` |

## Method Reference

Method reference (`Classe::metodo`) é uma forma ainda mais curta de lambda, usada quando o corpo da lambda apenas chama um método já existente, sem lógica adicional.

```java
// Lambda equivalente
Function<String, Integer> tamanho1 = texto -> texto.length();

// Method reference
Function<String, Integer> tamanho2 = String::length;
```

Existem quatro formas principais de method reference:

```java
// Método estático
Function<String, Integer> paraInteiro = Integer::parseInt;

// Método de instância de um objeto específico
String prefixo = "Sr. ";
Function<String, String> comPrefixo = prefixo::concat;

// Método de instância de um tipo arbitrário (o parâmetro da lambda vira o "dono" da chamada)
Function<String, Integer> tamanho = String::length; // equivalente a texto -> texto.length()

// Construtor
Supplier<ArrayList<String>> novaLista = ArrayList::new;
```

## Optional

`Optional<T>` é um container que representa um valor que pode ou não estar presente, usado como alternativa mais segura ao retorno de `null`. Em vez de um método retornar diretamente `null` quando não há valor, ele retorna um `Optional`, que por dentro pode conter o valor ou estar vazio.

```java
public Optional<Usuario> buscarUsuario(int id) {
    Usuario usuario = repositorio.encontrar(id); // pode ser null

    return Optional.ofNullable(usuario); // embrulha o valor, ou cria um Optional vazio se for null
}
```

### Criando um Optional

| Método | Uso |
| ------ | --- |
| `Optional.of(valor)` | Cria um `Optional` com um valor garantidamente não nulo, lança `NullPointerException` se o valor for `null` |
| `Optional.ofNullable(valor)` | Cria um `Optional`, vazio se o valor for `null`, com o valor caso contrário |
| `Optional.empty()` | Cria um `Optional` já vazio, explicitamente |

```java
Optional<String> a = Optional.of("Java");         // contém "Java"
Optional<String> b = Optional.ofNullable(null);   // vazio, sem lançar erro
Optional<String> c = Optional.empty();            // vazio, explícito
```

### Verificando presença

```java
Optional<Usuario> resultado = buscarUsuario(1);

if (resultado.isPresent()) {
    Usuario usuario = resultado.get();
    System.out.println(usuario.getNome());
}

if (resultado.isEmpty()) {
    System.out.println("Usuário não encontrado");
}
```

`get()` lança `NoSuchElementException` se chamado num `Optional` vazio, por isso o uso direto de `isPresent()` seguido de `get()` é considerado menos idiomático do que as alternativas a seguir.

### Consumindo o valor de forma mais direta

```java
// ifPresent, recebe um Consumer, executado só se houver valor
resultado.ifPresent(usuario -> System.out.println(usuario.getNome()));

// ifPresentOrElse (Java 9+), recebe um Consumer e um Runnable para o caso vazio
resultado.ifPresentOrElse(
    usuario -> System.out.println(usuario.getNome()),
    () -> System.out.println("Usuário não encontrado")
);
```

### Valores padrão

```java
// orElse, sempre calcula o valor padrão, mesmo quando não é necessário
Usuario usuario = resultado.orElse(new Usuario("Desconhecido"));

// orElseGet, recebe um Supplier, calculado só se o Optional estiver vazio
Usuario usuario2 = resultado.orElseGet(() -> new Usuario("Desconhecido"));

// orElseThrow, lança uma exceção customizada se estiver vazio
Usuario usuario3 = resultado.orElseThrow(() -> new UsuarioNaoEncontradoException("ID não encontrado"));
```

`orElseGet` é preferível a `orElse` quando o valor padrão exige algum processamento custoso, já que só é executado quando realmente necessário. `orElse` sempre executa a criação do valor padrão, mesmo quando o `Optional` já contém um valor.

### Transformando o valor com map

```java
Optional<Usuario> resultado = buscarUsuario(1);

Optional<String> nome = resultado.map(Usuario::getNome); // aplica a Function só se houver valor

System.out.println(nome.orElse("Sem nome"));
```

`map()` recebe uma `Function`, aplicada somente se o `Optional` contiver um valor. Se estiver vazio, o resultado continua sendo um `Optional` vazio, sem lançar erro.

### Uso recomendado

`Optional` é indicado como **tipo de retorno** de métodos, sinalizando explicitamente que o valor pode não existir. Não é recomendado como tipo de atributo de uma classe, nem como tipo de parâmetro de método, usos considerados fora do propósito original da API.

```java
public class Usuario {
    private Integer id;
    private Optional<String> nome; // Não recomendado
}
```


## Lambda em Collections

Vários métodos de Collections, já usados anteriormente, recebem uma interface funcional como parâmetro:

```java
List<Anime> animes = new ArrayList<>();
// ...

// removeIf recebe um Predicate<T>
animes.removeIf(anime -> anime.getEpisodios() == 0);

// forEach recebe um Consumer<T>
animes.forEach(anime -> System.out.println(anime.getNome()));

// sort recebe um Comparator<T>, que também é uma interface funcional
animes.sort((a, b) -> a.getNome().compareTo(b.getNome()));

// Comparator.comparing recebe uma Function<T, R>
animes.sort(Comparator.comparing(Anime::getNome));

// Map.forEach recebe um BiConsumer<K, V> (variante de dois parâmetros)
Map<String, Integer> idades = new HashMap<>();
idades.forEach((chave, valor) -> System.out.println(chave + ": " + valor));

// computeIfAbsent recebe uma Function<K, V>
Map<Character, List<String>> agrupado = new HashMap<>();
agrupado.computeIfAbsent('a', chave -> new ArrayList<>()).add("abacaxi");
```

Cada um desses métodos já foi usado em exercícios anteriores sem nomear formalmente qual interface funcional estava por trás. `removeIf` espera um `Predicate`, `forEach` espera um `Consumer`, `sort`/`comparing` espera um `Comparator` (que também segue a regra de único método abstrato).

## Comparando com Python

| Python | Java |
| ------ | ---- |
| `lambda x: x * 2` | `x -> x * 2` |
| `lambda a, b: a + b` | `(a, b) -> a + b` |
| Função nomeada com `def`, usada como valor | Method reference (`Classe::metodo`) |
| Tipagem dinâmica, qualquer callable serve | Precisa corresponder a uma interface funcional específica |
| `filter(lambda x: x % 2 == 0, lista)` | `lista.removeIf(x -> x % 2 != 0)` ou Predicate em Streams |
| `map(lambda x: x * 2, lista)` | `Function` aplicada via Streams |

A diferença central é a mesma que já apareceu em Enums e Generics: em Python, uma lambda é aceita em qualquer lugar que espera um callable, sem restrição de tipo. Em Java, uma lambda só é válida onde uma interface funcional específica é esperada, e o compilador verifica que a assinatura (quantidade de parâmetros, tipo de retorno) é compatível com o único método abstrato daquela interface.