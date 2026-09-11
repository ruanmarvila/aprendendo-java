# Streams

## Índice

- [O que é uma Stream](#o-que-é-uma-stream)
- [Criando uma Stream](#criando-uma-stream)
- [Operações Intermediárias x Terminais](#operações-intermediárias-x-terminais)
- [Operações Intermediárias](#operações-intermediárias)
  - [filter](#filter)
  - [map](#map)
  - [sorted](#sorted)
  - [distinct](#distinct)
  - [limit e skip](#limit-e-skip)
  - [peek](#peek)
- [Operações Terminais](#operações-terminais)
  - [collect](#collect)
  - [forEach](#foreach)
  - [reduce](#reduce)
  - [count, min, max](#count-min-max)
  - [anyMatch, allMatch, noneMatch](#anymatch-allmatch-nonematch)
  - [findFirst e findAny](#findfirst-e-findany)
- [Collectors Avançados](#collectors-avançados)
  - [groupingBy](#groupingby)
  - [partitioningBy](#partitioningby)
  - [toMap](#tomap)
  - [joining](#joining)
- [flatMap](#flatmap)
- [Streams Numéricas (IntStream, DoubleStream, LongStream)](#streams-numéricas-intstream-doublestream-longstream)
- [Streams Paralelas](#streams-paralelas)
- [Ordem das Operações (comparação com SQL)](#ordem-das-operações-comparação-com-sql)
- [Comparando com Python](#comparando-com-python)

## O que é uma Stream

Uma Stream é uma sequência de elementos que suporta operações funcionais encadeadas, como filtrar, transformar e agregar dados, sem alterar a estrutura de dados de origem. Não é uma estrutura de dados em si, é um pipeline de processamento sobre uma fonte de dados já existente (uma `Collection`, um array, ou outra fonte).

```java
List<String> nomes = List.of("Ana", "Bruno", "Carlos");

long quantidade = nomes.stream()
        .filter(nome -> nome.length() > 3)
        .count();

System.out.println(quantidade); // 2
```

Uma Stream não armazena elementos, apenas processa os elementos da fonte, sob demanda, e não modifica a fonte original.

```java
List<String> original = new ArrayList<>(List.of("Ana", "Bruno"));

original.stream().filter(nome -> nome.length() > 3).toList();

System.out.println(original); // [Ana, Bruno], inalterada
```

Uma Stream só pode ser percorrida uma vez. Depois de uma operação terminal ser executada, a mesma instância de Stream não pode ser reutilizada.

```java
Stream<String> stream = nomes.stream();
stream.forEach(System.out::println);
stream.forEach(System.out::println); // IllegalStateException, stream já consumida
```

## Criando uma Stream

| Origem | Método |
| ------ | ------ |
| `Collection` (`List`, `Set`) | `.stream()` |
| Array | `Arrays.stream(array)` |
| Valores diretos | `Stream.of(valor1, valor2, ...)` |
| `Map` | `.entrySet().stream()`, `.keySet().stream()` ou `.values().stream()` |
| Stream vazia | `Stream.empty()` |
| Stream infinita, gerada | `Stream.generate(supplier)` ou `Stream.iterate(inicial, funcao)` |

```java
List<Integer> lista = List.of(1, 2, 3);
Stream<Integer> stream1 = lista.stream();

Integer[] array = {1, 2, 3};
Stream<Integer> stream2 = Arrays.stream(array);

Stream<Integer> stream3 = Stream.of(1, 2, 3);

Stream<Integer> stream4 = Stream.iterate(1, n -> n * 2).limit(5); // 1, 2, 4, 8, 16

Stream<int[]> fibonacci = Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]});
fibonacci.limit(10).forEach(a -> System.out.println(Arrays.toString(a)));

Stream<Double> aleatorios = Stream.generate(Math::random);
aleatorios.limit(5).forEach(System.out::println); // 5 valores aleatórios, sem relação entre eles

Stream<String> repetido = Stream.generate(() -> "Java");
repetido.limit(3).forEach(System.out::println); // "Java", "Java", "Java"
```

## Operações Intermediárias x Terminais

Uma Stream é processada em duas categorias de operações:

**Intermediárias**, retornam outra Stream, permitindo encadeamento, e são **preguiçosas** (lazy), não executam nada sozinhas.

**Terminais**, retornam um resultado final (uma lista, um valor, `void`), e são o gatilho que efetivamente executa toda a cadeia de operações intermediárias anteriores.

```java
Stream<String> stream = nomes.stream()
        .filter(nome -> nome.length() > 3); // nada é executado ainda, aqui

long resultado = stream.count(); // só agora a cadeia inteira roda, do início ao fim
```

Sem uma operação terminal ao final, nenhuma operação intermediária é executada.

```java
nomes.stream().filter(nome -> {
    System.out.println("Filtrando: " + nome); // nunca imprime nada
    return nome.length() > 3;
});
```

## Operações Intermediárias

### filter

Recebe um `Predicate`, mantém somente os elementos que satisfazem a condição.

```java
List<Integer> pares = List.of(1, 2, 3, 4, 5, 6).stream()
        .filter(n -> n % 2 == 0)
        .toList(); // [2, 4, 6]
```

### map

Recebe uma `Function`, transforma cada elemento em outro valor, possivelmente de tipo diferente.

```java
List<Integer> tamanhos = nomes.stream()
        .map(String::length)
        .toList(); // [3, 5, 7]
```

### sorted

Ordena os elementos, usando `compareTo()` (ordenação natural) ou um `Comparator` customizado.

```java
List<String> ordenados = nomes.stream()
        .sorted()
        .toList();

List<String> ordenadosPorTamanho = nomes.stream()
        .sorted(Comparator.comparingInt(String::length))
        .toList();
```

### distinct

Remove elementos duplicados, usando `equals()` para comparação.

```java
List<Integer> unicos = List.of(1, 2, 2, 3, 3, 3).stream()
        .distinct()
        .toList(); // [1, 2, 3]
```

### limit e skip

`limit(n)` mantém só os primeiros `n` elementos. `skip(n)` descarta os primeiros `n` elementos, mantendo o restante.

```java
List<Integer> numeros = List.of(1, 2, 3, 4, 5);

List<Integer> primeiros3 = numeros.stream().limit(3).toList();     // [1, 2, 3]
List<Integer> apos2 = numeros.stream().skip(2).toList();           // [3, 4, 5]
List<Integer> pagina = numeros.stream().skip(2).limit(2).toList(); // [3, 4], paginação
```

### peek

Executa um `Consumer` sobre cada elemento, sem alterar a Stream, usado principalmente para depuração.

```java
List<Integer> resultado = numeros.stream()
        .peek(n -> System.out.println("Processando: " + n))
        .filter(n -> n % 2 == 0)
        .toList();
```

Não é recomendado usar `peek` para efeitos colaterais em código de produção, apenas para inspeção durante desenvolvimento.

## Operações Terminais

### collect

Reúne o resultado da Stream em uma estrutura, geralmente usando `Collectors`.

```java
List<String> lista = nomes.stream().collect(Collectors.toList());
Set<String> conjunto = nomes.stream().collect(Collectors.toSet());

List<String> lista2 = nomes.stream().toList(); // forma moderna, Java 16+, equivalente a Collectors.toList()
```

### forEach

Executa um `Consumer` sobre cada elemento, sem retorno.

```java
nomes.stream().forEach(nome -> System.out.println(nome));
nomes.stream().forEach(System.out::println); // equivalente, com method reference
```

### reduce

Combina todos os elementos em um único resultado, aplicando uma operação acumuladora repetidamente.

```java
List<Integer> numeros = List.of(1, 2, 3, 4, 5);

int soma = numeros.stream().reduce(0, (acumulado, atual) -> acumulado + atual);
System.out.println(soma); // 15

Optional<Integer> maior = numeros.stream().reduce((a, b) -> a > b ? a : b);
System.out.println(maior.get()); // 5
```

A versão com valor inicial (`reduce(0, ...)`) sempre retorna um valor direto. A versão sem valor inicial retorna `Optional`, pois a Stream pode estar vazia.

### count, min, max

```java
long quantidade = numeros.stream().count();

Optional<Integer> menor = numeros.stream().min(Comparator.naturalOrder());
Optional<Integer> maior = numeros.stream().max(Comparator.naturalOrder());
```

### anyMatch, allMatch, noneMatch

Recebem um `Predicate`, retornam `boolean`, verificando a condição sobre o conjunto de elementos.

```java
boolean algumPar = numeros.stream().anyMatch(n -> n % 2 == 0);   // true
boolean todosPositivos = numeros.stream().allMatch(n -> n > 0);  // true
boolean nenhumNegativo = numeros.stream().noneMatch(n -> n < 0); // true
```

### findFirst e findAny

Retornam `Optional`, com o primeiro elemento encontrado (`findFirst`) ou qualquer um (`findAny`, mais eficiente em Streams paralelas, pois não precisa respeitar ordem).

```java
Optional<Integer> primeiro = numeros.stream().filter(n -> n > 2).findFirst();
System.out.println(primeiro.orElse(-1)); // 3
```

## Collectors Avançados

### groupingBy

Agrupa os elementos em um `Map`, com base em uma `Function` que define a chave de agrupamento.

```java
List<String> palavras = List.of("ana", "bruno", "ana", "carla", "bruno");

Map<Integer, List<String>> agrupadoPorTamanho = palavras.stream()
        .collect(Collectors.groupingBy(String::length));

System.out.println(agrupadoPorTamanho); // {3=[ana, ana], 5=[bruno, carla, bruno]}
```

Pode ser combinado com um segundo `Collector`, aplicado sobre cada grupo.

```java
Map<Integer, Long> contagemPorTamanho = palavras.stream()
        .collect(Collectors.groupingBy(String::length, Collectors.counting()));

System.out.println(contagemPorTamanho); // {3=2, 5=3}
```

### partitioningBy

Divide os elementos em exatamente dois grupos (`true`/`false`), com base em um `Predicate`.

```java
Map<Boolean, List<Integer>> particionado = numeros.stream()
        .collect(Collectors.partitioningBy(n -> n % 2 == 0));

System.out.println(particionado); // {false=[1, 3, 5], true=[2, 4]}
```

### toMap

Coleta os elementos em um `Map`, especificando funções para gerar a chave e o valor.

```java
Map<String, Integer> mapa = nomes.stream()
        .collect(Collectors.toMap(nome -> nome, String::length));

System.out.println(mapa); // {Ana=3, Bruno=5, Carlos=7}
```

Se houver chaves duplicadas, `toMap` lança exceção por padrão. É possível informar uma função de merge como terceiro argumento, para resolver o conflito.

```java
Map<Integer, String> semConflito = palavras.stream()
        .collect(Collectors.toMap(
                String::length,
                palavra -> palavra,
                (existente, novo) -> existente + ", " + novo // resolve duplicidade de chave
        ));
```

### joining

Concatena elementos de uma Stream de Strings em uma única String, com separador, prefixo e sufixo opcionais.

```java
String resultado = nomes.stream().collect(Collectors.joining());
System.out.println(resultado); // AnaBrunoCarlos

String comVirgula = nomes.stream().collect(Collectors.joining(", "));
System.out.println(comVirgula); // Ana, Bruno, Carlos

String comColchetes = nomes.stream().collect(Collectors.joining(", ", "[", "]"));
System.out.println(comColchetes); // [Ana, Bruno, Carlos]
```

## flatMap

Usado quando cada elemento da Stream original é, por sua vez, uma coleção, achatando tudo em uma única Stream de nível único.

```java
List<List<Integer>> listaDeListas = List.of(
        List.of(1, 2, 3),
        List.of(4, 5),
        List.of(6)
);

List<Integer> achatada = listaDeListas.stream()
        .flatMap(lista -> lista.stream())
        .toList(); // [1, 2, 3, 4, 5, 6]
```

A diferença central para `map`: `map` transformaria cada `List<Integer>` em outra coisa, mantendo o aninhamento (uma Stream de Streams). `flatMap` funde tudo em um único nível.

```java
// map sozinho manteria o aninhamento
Stream<Stream<Integer>> aninhado = listaDeListas.stream().map(List::stream);

// flatMap achata em um único nível
Stream<Integer> plano = listaDeListas.stream().flatMap(List::stream);
```

## Streams Numéricas (IntStream, DoubleStream, LongStream)

Versões especializadas de Stream para tipos primitivos numéricos, evitando o custo de autoboxing (converter `int` em `Integer` repetidamente) e adicionando métodos estatísticos próprios.

```java
IntStream intStream = IntStream.rangeClosed(1, 5); // 1, 2, 3, 4, 5 (inclusive)
IntStream intStream2 = IntStream.range(1, 5);       // 1, 2, 3, 4 (exclusive no fim)

int soma = IntStream.rangeClosed(1, 10).sum();
OptionalDouble media = IntStream.rangeClosed(1, 10).average();
IntSummaryStatistics estatisticas = IntStream.rangeClosed(1, 10).summaryStatistics();

System.out.println(estatisticas.getMax()); // 10
System.out.println(estatisticas.getMin()); // 1
```

Conversão entre Stream de objetos e Stream numérica:

```java
List<String> palavras = List.of("ana", "bruno", "carla");

int totalCaracteres = palavras.stream()
        .mapToInt(String::length) // Stream<String> para IntStream
        .sum();
```

## Streams Paralelas

Uma Stream pode ser processada em paralelo, dividindo o trabalho entre múltiplas threads automaticamente.

```java
long quantidade = numeros.parallelStream()
        .filter(n -> n % 2 == 0)
        .count();
```

Streams paralelas trazem ganho de desempenho apenas em volumes grandes de dados, com operações custosas o suficiente para compensar o overhead de gerenciar múltiplas threads. Para coleções pequenas, o custo de paralelizar costuma superar o ganho, tornando o processamento sequencial mais rápido na prática.

Operações com efeitos colaterais (`peek`, `forEach` modificando variáveis externas) não são seguras em Streams paralelas, pois a ordem de execução entre threads não é garantida.

## Ordem das Operações (comparação com SQL)

A ordem das operações intermediárias em uma Stream segue uma lógica próxima da encontrada em uma query SQL.

```sql
SELECT titulo FROM light_novels
WHERE preco <= 50.0
ORDER BY titulo
LIMIT 3
```

```java
List<String> titulos = novels.stream()
        .filter(ln -> ln.getPreco() <= 50.0)                   // WHERE
        .sorted(Comparator.comparing(LightNovel::getTitulo))   // ORDER BY
        .limit(3)                                              // LIMIT
        .map(LightNovel::getTitulo)                            // SELECT (projeção)
        .collect(Collectors.toList());
```

Existem dois tipos de dependência entre operações, que afetam a ordem de formas diferentes:

**Operações independentes entre si**, como `filter` e `sorted`, produzem o mesmo resultado final independente da ordem entre elas, mas a ordem ainda afeta performance. Filtrar antes de ordenar reduz a quantidade de elementos que `sorted` precisa processar.

**Operações dependentes do resultado uma da outra**, como `sorted` e `limit`, exigem uma ordem específica para produzir o resultado correto. Colocar `limit` antes de `sorted` pega elementos da ordem original, e só então os ordena entre si, um resultado diferente de "os N primeiros segundo o critério de ordenação".

Diferente do SQL, onde um otimizador de query pode reorganizar a ordem física de execução por trás dos panos, em Stream a ordem escrita no código é exatamente a ordem de execução, sem uma camada de otimização automática corrigindo eventuais erros de sequência.

## Comparando com Python

| Python | Java |
| ------ | ---- |
| `filter(lambda x: x % 2 == 0, lista)` | `.filter(x -> x % 2 == 0)` |
| `map(lambda x: x * 2, lista)` | `.map(x -> x * 2)` |
| List comprehension: `[x for x in lista if x > 2]` | `.stream().filter(x -> x > 2).toList()` |
| `sorted(lista)` | `.sorted()` |
| `sorted(lista, key=lambda x: x.nome)` | `.sorted(Comparator.comparing(X::getNome))` |
| `functools.reduce(lambda a, b: a + b, lista)` | `.reduce(0, (a, b) -> a + b)` |
| `any(x > 2 for x in lista)` | `.anyMatch(x -> x > 2)` |
| `all(x > 0 for x in lista)` | `.allMatch(x -> x > 0)` |
| Generator (lazy, avaliado sob demanda) | Stream (lazy, operações intermediárias) |

A diferença central de novo é tipagem. List comprehensions e generators do Python funcionam com qualquer tipo, sem verificação em tempo de compilação. Streams em Java são fortemente tipadas em cada etapa da cadeia, o compilador verifica a compatibilidade de tipos entre cada `map`/`filter`/`collect` antes mesmo do código rodar.