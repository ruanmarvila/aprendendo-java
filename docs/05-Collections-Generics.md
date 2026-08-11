# Collections e Generics

## Índice

- [O que é o Framework Collections](#o-que-é-o-framework-collections)
- [List](#list)
  - [ArrayList](#arraylist)
    - [Capacidade e Crescimento](#capacidade-e-crescimento)
  - [LinkedList](#linkedlist)
  - [ArrayList x LinkedList](#arraylist-x-linkedlist)
- [Queue e Deque](#queue-e-deque)
  - [Deque](#deque)
  - [Implementações](#implementações)
- [Set](#set)
  - [Métodos](#métodos-principais-de-set)
  - [HashSet](#hashset)
  - [LinkedHashSet](#linkedhashset)
  - [TreeSet](#treeset)
  - [Comparação](#comparação-entre-as-três-implementações)
  - [SortedSet](#sortedset)
- [Map](#map)
  - [Métodos](#métodos-principais-de-map)
  - [Percorrendo Map](#percorrendo-um-map)
  - [HashMap](#hashmap)
  - [LinkedHashMap](#linkedhashmap)
  - [TreeMap](#treemap)
  - [Comparação](#comparação-entre-as-três-implementações-1)
- [Iterator](#iterator)
  - [Porquê](#por-que-usar-iterator-em-vez-de-for-each)
  - [removeIf](#removeif-como-alternativa-mais-direta)
  - [ListIterator](#listiterator)
- [Comparable x Comparator](#comparable-x-comparator)
  - [Comparable](#comparable)
  - [Comparator](#comparator)
  - [Usos](#quando-usar-cada-um)
  - [Impacto em Tree](#impacto-em-treeset-e-treemap)
- [Classe Collections](#classe-collections-utilitários)
  - [Listas imutáveis](#listas-imutáveis)
- [Generics](#generics)
  - [Classes Genéricas](#classes-genéricas)
  - [Métodos Genéricos](#métodos-genéricos)
  - [Bounded Types](#bounded-types-restringindo-o-tipo-aceito)
  - [Wildcards](#wildcards)
  - [Generics em Collections](#generics-em-collections)
  
---

## O que é o Framework Collections

O **Java Collections Framework** é um conjunto de interfaces e classes prontas para armazenar e manipular grupos de elementos, evitando que você precise implementar estruturas de dados (listas, conjuntos, mapas) do zero.

Ele é organizado em torno de algumas interfaces principais, que definem **o que** uma estrutura faz, deixando **como** ela faz para as classes que a implementam:

```
Collection
├── List    (elementos ordenados, permite duplicados)
├── Set     (elementos únicos, sem duplicados)
└── Queue   (fila, elementos processados em ordem)

Map (não é uma Collection — armazena pares chave-valor)
```

- **`Collection`** é a interface raiz de `List`, `Set` e `Queue`, qualquer estrutura que "guarda vários elementos" deriva dela.
- **`Map`** fica de fora dessa hierarquia, porque não representa "um grupo de elementos", e sim uma associação entre uma chave e um valor, por isso ele tem sua própria hierarquia separada (`Map` → `HashMap`, `TreeMap`, etc.).

Cada uma dessas interfaces tem múltiplas implementações, cada uma otimizada pra um cenário diferente, é aí que entram `ArrayList`, `LinkedList`, `HashSet`, `TreeMap` e etc.

## List

`List` é uma interface que representa uma **coleção ordenada**, onde os elementos mantêm a posição em que foram inseridos (ou a posição em que você os colocou manualmente) e permitem duplicados.

```java
List<String> nomes = new ArrayList<>();
nomes.add("Ana");
nomes.add("Bruno");
nomes.add("Ana"); // duplicado permitido

System.out.println(nomes); // [Ana, Bruno, Ana] -- mantém a ordem de inserção
```

`List` não pode ser instanciada diretamente (`new List<>()` não existe), você sempre instancia uma das suas implementações, sendo as duas principais `ArrayList` e `LinkedList`.

### ArrayList

`ArrayList` é a implementação mais usada de `List`, funcionando internamente como um array redimensionável. Bom para acesso rápido por índice, e para a maioria dos casos de uso do dia a dia.

```java
List<String> animes = new ArrayList<>();
animes.add("Naruto");
animes.add("Bleach");

System.out.println(animes.get(0)); // "Naruto" -- acesso rápido por índice
```

#### Capacidade e Crescimento

Por trás dos panos, um `ArrayList` vazio (`new ArrayList<>()`) começa com uma capacidade padrão de **10** posições, mesmo que você não tenha adicionado nada ainda. Quando essa capacidade se esgota, o `ArrayList` cria um **novo array maior**, copia todos os elementos pra ele, e descarta o array antigo.

O crescimento não dobra o tamanho, ele aumenta em **50%** da capacidade atual:

```
novaCapacidade = capacidadeAtual + (capacidadeAtual / 2)
```

Exemplo: um `ArrayList` com capacidade 10, ao precisar crescer, passa para 15; se precisar crescer de novo, vai para 22, e assim por diante.

Se você já sabe de antemão que vai guardar muitos elementos, é possível evitar esses redimensionamentos sucessivos informando a capacidade inicial:

```java
List<String> animes = new ArrayList<>(100); // já começa com capacidade para 100 elementos
```

Isso evita cópias desnecessárias de array conforme a lista cresce, uma otimização pequena, mas útil quando você sabe o tamanho aproximado dos dados de antemão.


### LinkedList

Implementação de `List` baseada em uma lista **duplamente ligada**, cada elemento guarda referência ao anterior e ao próximo. Boa para inserções/remoções frequentes nas pontas (início ou fim), mas lenta para acesso por índice.

```java
List<String> fila = new LinkedList<>();
fila.add("Primeiro");
fila.add("Segundo");
```

`LinkedList` também implementa `Deque`, o que permite usá-la como fila ou pilha, com métodos próprios:

```java
LinkedList<String> deque = new LinkedList<>();
deque.addFirst("Início");
deque.addLast("Fim");

System.out.println(deque.getFirst()); // "Início"
System.out.println(deque.getLast());  // "Fim"
```

### ArrayList x LinkedList

| | `ArrayList` | `LinkedList` |
| --- | --- | --- |
| Estrutura interna | Array redimensionável | Lista duplamente ligada |
| Acesso por índice (`get(i)`) | Rápido | Lento (percorre nó por nó) |
| Inserir/remover no início | Lento (desloca elementos) | Rápido |
| Inserir/remover no fim | Rápido | Rápido |
| Inserir/remover no meio | Lento (desloca elementos) | Rápido para reconectar, lento para localizar |
| Uso de memória | Menor | Maior (referências extras por nó) |
| Também implementa | — | `Deque` (fila de duas pontas) |

> Na prática, `ArrayList` é o padrão para a maioria dos casos, a maior parte do código percorre listas com `for-each` ou acessa por índice, cenário onde ela é mais eficiente. `LinkedList` vale a pena quando o uso principal é como fila/pilha, com muitas inserções e remoções nas pontas.


## Queue e Deque

`Queue` representa uma fila, estrutura onde os elementos são processados em uma ordem específica, geralmente **FIFO** (First In, First Out, o primeiro a entrar é o primeiro a sair).

```java
Queue<String> fila = new LinkedList<>();
fila.offer("Primeiro");
fila.offer("Segundo");
fila.offer("Terceiro");

System.out.println(fila.poll()); // "Primeiro", remove e retorna o início da fila
System.out.println(fila.peek()); // "Segundo", só consulta o início, sem remover
```

| Método | Função | Comportamento se a operação falhar |
| ------ | ------ | ------------------------------------ |
| `offer(elemento)` | Insere um elemento no fim da fila | Retorna `false` |
| `poll()` | Remove e retorna o elemento do início | Retorna `null` |
| `peek()` | Consulta o elemento do início, sem remover | Retorna `null` |
| `add(elemento)` | Equivalente a `offer`, mas lança exceção em vez de retornar `false` | Lança `IllegalStateException` |
| `remove()` | Equivalente a `poll`, mas lança exceção em vez de retornar `null` | Lança `NoSuchElementException` |
| `element()` | Equivalente a `peek`, mas lança exceção em vez de retornar `null` | Lança `NoSuchElementException` |

A diferença entre os dois grupos de métodos é o tratamento de erro. `offer`/`poll`/`peek` retornam um valor especial (`false`/`null`) quando a operação não é possível, enquanto `add`/`remove`/`element` lançam exceção. A convenção geral é preferir `offer`/`poll`/`peek` quando falhar é uma situação esperada, e `add`/`remove`/`element` quando falhar indica um erro real no programa.

### Deque

`Deque` (Double Ended Queue) é uma fila de **duas pontas**, permitindo inserir e remover tanto no início quanto no fim. Pode funcionar tanto como fila (FIFO) quanto como pilha (LIFO, Last In, First Out).

```java
Deque<String> deque = new ArrayDeque<>();

deque.addFirst("Início");
deque.addLast("Fim");

System.out.println(deque.peekFirst()); // "Início"
System.out.println(deque.peekLast());  // "Fim"
```

| Método | Função |
| ------ | ------ |
| `addFirst(elemento)` / `addLast(elemento)` | Insere no início / fim |
| `removeFirst()` / `removeLast()` | Remove e retorna o elemento do início / fim |
| `peekFirst()` / `peekLast()` | Consulta o elemento do início / fim, sem remover |
| `push(elemento)` | Insere no início (uso como pilha) |
| `pop()` | Remove e retorna o elemento do início (uso como pilha) |

**Como pilha (LIFO):**

```java
Deque<Integer> pilha = new ArrayDeque<>();
pilha.push(1);
pilha.push(2);
pilha.push(3);

System.out.println(pilha.pop()); // 3, o último inserido é o primeiro removido
```

> A classe `Stack` (mais antiga, anterior ao Collections Framework) ainda existe em Java, mas o uso de `Deque` para pilha é a forma recomendada atualmente, por ser mais consistente com o resto do framework.

### Implementações

`LinkedList` implementa tanto `Queue` quanto `Deque`, e `ArrayDeque` implementa `Deque` (baseada em array, geralmente mais eficiente que `LinkedList` para esse uso).

| | `LinkedList` | `ArrayDeque` |
| --- | --- | --- |
| Estrutura interna | Lista duplamente ligada | Array circular redimensionável |
| Implementa | `List`, `Queue`, `Deque` | `Deque` |
| Desempenho geral | Mais lento | Mais rápido, geralmente preferido para fila/pilha |


## Set

`Set` é uma interface que representa uma **coleção sem elementos duplicados**. Diferente de `List`, não garante posição por índice, o foco é unicidade, não ordem.

```java
Set<String> nomes = new HashSet<>();
nomes.add("Ana");
nomes.add("Bruno");
nomes.add("Ana"); // ignorado, já existe

System.out.println(nomes.size()); // 2
```

Existem três implementações principais, cada uma com uma estratégia diferente de organizar os elementos internamente.

### Métodos Principais de Set

| Método | Função |
| ------ | ------ |
| `add(elemento)` | Adiciona um elemento (ignorado se já existir) |
| `remove(elemento)` | Remove um elemento |
| `contains(elemento)` | Verifica se o elemento existe no conjunto |
| `size()` | Retorna a quantidade de elementos |
| `isEmpty()` | Verifica se o conjunto está vazio |
| `clear()` | Remove todos os elementos |
| `addAll(colecao)` | Adiciona todos os elementos de outra coleção (união) |
| `retainAll(colecao)` | Mantém só os elementos que também existem na outra coleção (interseção) |
| `removeAll(colecao)` | Remove todos os elementos que existem na outra coleção (diferença) |

Os três últimos métodos permitem operações de conjunto no sentido matemático:

```java
Set<Integer> a = new HashSet<>(List.of(1, 2, 3, 4));
Set<Integer> b = new HashSet<>(List.of(3, 4, 5, 6));

Set<Integer> uniao = new HashSet<>(a);
uniao.addAll(b); // [1, 2, 3, 4, 5, 6]

Set<Integer> intersecao = new HashSet<>(a);
intersecao.retainAll(b); // [3, 4]

Set<Integer> diferenca = new HashSet<>(a);
diferenca.removeAll(b); // [1, 2]
```

### HashSet

Implementação baseada em tabela hash, a posição de cada elemento é definida pelo `hashCode()` dele, não pela ordem de inserção. Ao percorrer com `for-each`, a ordem que aparece é imprevisível.

```java
Set<String> frutas = new HashSet<>();
frutas.add("Banana");
frutas.add("Maçã");
frutas.add("Uva");

System.out.println(frutas); // ordem imprevisível, ex: [Uva, Banana, Maçã]
```

É a implementação mais rápida para adicionar, remover e verificar existência (`contains()`), justamente por não precisar manter nenhuma ordem.

> Depende diretamente de `equals()`/`hashCode()` estarem bem implementados na classe armazenada — são esses métodos que decidem se dois elementos são "iguais" e onde cada um é posicionado internamente.

### LinkedHashSet

Mesma base de tabela hash do `HashSet`, mas adiciona referências de "anterior/próximo" entre os elementos, preservando a ordem de inserção ao percorrer.

```java
Set<String> frutas = new LinkedHashSet<>();
frutas.add("Banana");
frutas.add("Maçã");
frutas.add("Uva");

System.out.println(frutas); // sempre: [Banana, Maçã, Uva]
```

Custo extra de memória (as referências adicionais) em troca de ordem previsível.

### TreeSet

Implementação que mantém os elementos **sempre ordenados**, reordenando a cada inserção. Implementa a interface `NavigableSet`, que soma métodos de navegação (`first()`, `last()`, `higher()`, `lower()`, `ceiling()`, `floor()`) que exploram o fato dos elementos estarem ordenados.

```java
Set<Integer> numeros = new TreeSet<>();
numeros.add(5);
numeros.add(1);
numeros.add(3);

System.out.println(numeros); // [1, 3, 5] -- sempre ordenado
```

Por padrão, usa a ordenação natural da classe (`compareTo()`, via `Comparable`). É possível passar um `Comparator` customizado no construtor, que passa a ser usado no lugar de `compareTo()`:

```java
Set<Anime> animesPorAno = new TreeSet<>(Comparator.comparingInt(Anime::getAno));
```

> **Atenção:** `TreeSet` não usa `equals()`/`hashCode()` para decidir se dois elementos são duplicados — usa `compareTo()`/`Comparator` retornando `0`. Se o critério de comparação usado for parcial (por exemplo, comparar só pelo nome de um produto que também tem armazenamento e preço), dois objetos diferentes podem ser tratados como duplicados e o segundo simplesmente não é adicionado, sem erro nem aviso. Nesses casos, o comparador precisa considerar todos os campos relevantes, ou usar `.thenComparing(...)` como critério de desempate.

### Comparação entre as três implementações

| | `HashSet` | `LinkedHashSet` | `TreeSet` |
| --- | --- | --- | --- |
| Ordem | Nenhuma garantida | Ordem de inserção | Sempre ordenado |
| Velocidade (add/remove/contains) | Mais rápida | Um pouco mais lenta que `HashSet` | Mais lenta (reordena a cada inserção) |
| Baseado em | Tabela hash | Tabela hash + lista ligada | Árvore balanceada |
| Depende de | `equals()`/`hashCode()` | `equals()`/`hashCode()` | `compareTo()`/`Comparator` |
| Implementa também | — | — | `NavigableSet` |

### SortedSet

`SortedSet` é a interface que `TreeSet` implementa (junto com `NavigableSet`, que estende `SortedSet` e adiciona os métodos de navegação já vistos). `SortedSet` sozinha garante só a ordenação e alguns métodos básicos ligados a ela:

| Método | Função |
| ------ | ------ |
| `first()` | Retorna o primeiro elemento (o "menor", segundo a ordenação) |
| `last()` | Retorna o último elemento (o "maior") |
| `headSet(limite)` | Retorna os elementos menores que `limite` |
| `tailSet(inicio)` | Retorna os elementos maiores ou iguais a `inicio` |
| `subSet(inicio, fim)` | Retorna os elementos entre `inicio` (incluso) e `fim` (excluso) |

```java
SortedSet<Integer> numeros = new TreeSet<>(List.of(10, 20, 30, 40, 50));

System.out.println(numeros.first()); // 10
System.out.println(numeros.last());  // 50
System.out.println(numeros.headSet(30)); // [10, 20]
System.out.println(numeros.tailSet(30)); // [30, 40, 50]
System.out.println(numeros.subSet(20, 40)); // [20, 30]
```

> `TreeSet` é declarado como `TreeSet<T>` na maioria dos exemplos práticos, mas a variável pode ser declarada como `SortedSet<T>` ou `NavigableSet<T>` quando só os métodos dessas interfaces forem necessários — reforça o princípio de programar voltado à interface, não à implementação.


## Map

`Map` armazena pares **chave-valor**. Não faz parte da hierarquia de `Collection`, representa uma associação, não um grupo de elementos avulsos. Cada chave é única; valores podem se repetir.

```java
Map<String, Integer> idades = new HashMap<>();
idades.put("Ana", 25);
idades.put("Bruno", 30);
idades.put("Ana", 26); // sobrescreve o valor anterior da chave "Ana"

System.out.println(idades.get("Ana")); // 26
System.out.println(idades.size());     // 2
```

### Métodos Principais de Map

| Método | Função |
| ------ | ------ |
| `put(chave, valor)` | Insere ou atualiza um par chave-valor |
| `get(chave)` | Retorna o valor associado à chave, ou `null` se não existir |
| `getOrDefault(chave, padrao)` | Retorna o valor associado, ou um valor padrão se a chave não existir |
| `remove(chave)` | Remove o par associado à chave |
| `containsKey(chave)` | Verifica se a chave existe |
| `containsValue(valor)` | Verifica se o valor existe em algum par |
| `size()` | Retorna a quantidade de pares |
| `keySet()` | Retorna um `Set` com todas as chaves |
| `values()` | Retorna uma `Collection` com todos os valores |
| `entrySet()` | Retorna um `Set<Map.Entry<K, V>>` com todos os pares |

### Percorrendo um Map

```java
Map<String, Integer> idades = new HashMap<>();
idades.put("Ana", 25);
idades.put("Bruno", 30);

// Pares chave-valor, via entrySet()
for (Map.Entry<String, Integer> entrada : idades.entrySet()) {
    System.out.println(entrada.getKey() + ": " + entrada.getValue());
}

// Só chaves
for (String chave : idades.keySet()) {
    System.out.println(chave);
}

// Só valores
for (Integer valor : idades.values()) {
    System.out.println(valor);
}

// Com forEach + lambda
idades.forEach((chave, valor) -> System.out.println(chave + ": " + valor));
```

### HashMap

Implementação mais usada de `Map`, baseada em tabela hash, a posição de cada par é definida pelo `hashCode()` da chave. Não garante ordem ao percorrer.

```java
Map<String, Integer> mapa = new HashMap<>();
mapa.put("Banana", 3);
mapa.put("Maçã", 5);

System.out.println(mapa); // ordem imprevisível
```

Capacidade padrão de **16**, dobrando de tamanho quando o fator de carga (padrão 0.75, ou seja, 75% de ocupação) é atingido.

### LinkedHashMap

Mesma base de `HashMap`, com referências extras que preservam a ordem de inserção ao percorrer.

```java
Map<String, Integer> mapa = new LinkedHashMap<>();
mapa.put("Banana", 3);
mapa.put("Maçã", 5);

System.out.println(mapa); // sempre: {Banana=3, Maçã=5}
```

### TreeMap

Mantém as chaves sempre ordenadas — por ordenação natural (`compareTo()` da chave) ou por `Comparator` customizado, passado no construtor. Implementa `SortedMap`/`NavigableMap`, com métodos equivalentes aos vistos em `SortedSet`/`NavigableSet` (`firstKey()`, `lastKey()`, `headMap()`, `tailMap()`, etc.).

```java
Map<String, Integer> mapa = new TreeMap<>();
mapa.put("Banana", 3);
mapa.put("Abacaxi", 2);
mapa.put("Maçã", 5);

System.out.println(mapa); // {Abacaxi=2, Banana=3, Maçã=5} -- ordenado pela chave
```

### Comparação entre as três implementações

| | `HashMap` | `LinkedHashMap` | `TreeMap` |
| --- | --- | --- | --- |
| Ordem | Nenhuma garantida | Ordem de inserção | Ordenado pela chave |
| Velocidade (put/get/remove) | Mais rápida | Um pouco mais lenta que `HashMap` | Mais lenta (reordena a cada inserção) |
| Baseado em | Tabela hash | Tabela hash + lista ligada | Árvore balanceada |
| Depende de | `equals()`/`hashCode()` da chave | `equals()`/`hashCode()` da chave | `compareTo()`/`Comparator` da chave |
| Capacidade padrão | 16 (dobra ao crescer) | 16 (dobra ao crescer) | Sem capacidade fixa (árvore) |

> Padrão que se repete em `Set` e `Map`: as três implementações seguem exatamente a mesma lógica de trade-off — hash (rápido, sem ordem) → hash + lista ligada (ordem de inserção, custo extra de memória) → árvore (sempre ordenado, mais lento). O nome muda (`HashSet`/`HashMap`, `LinkedHashSet`/`LinkedHashMap`, `TreeSet`/`TreeMap`), a estratégia interna é a mesma.


## Iterator

`Iterator` é a interface que define como percorrer uma `Collection` manualmente, elemento por elemento, com controle total sobre quando avançar e quando remover.

```java
List<String> animes = new ArrayList<>(List.of("Naruto", "Bleach", "One Piece"));

Iterator<String> it = animes.iterator();

while (it.hasNext()) {
    String anime = it.next();
    System.out.println(anime);
}
```

| Método | Função |
| ------ | ------ |
| `hasNext()` | Verifica se existe um próximo elemento |
| `next()` | Retorna o próximo elemento e avança o cursor |
| `remove()` | Remove o último elemento retornado por `next()` |

### Por que usar Iterator em vez de for-each

O `for-each` (`for (String anime : animes)`) é, por baixo dos panos, um `Iterator` disfarçado, o compilador o transforma automaticamente numa estrutura equivalente ao `while (it.hasNext())` acima. A diferença é que o `for-each` não expõe o `Iterator`, então não é possível chamar `remove()` durante ele.

Remover elementos de uma coleção **durante** um `for-each` lança `ConcurrentModificationException`:

```java
for (String anime : animes) {
    if (anime.equals("Bleach")) {
        animes.remove(anime); // ConcurrentModificationException!
    }
}
```

Usando `Iterator` diretamente, a remoção é segura, porque é o próprio `Iterator` que remove, avisando a coleção internamente:

```java
Iterator<String> it = animes.iterator();

while (it.hasNext()) {
    String anime = it.next();
    if (anime.equals("Bleach")) {
        it.remove(); // seguro
    }
}
```

### removeIf como alternativa mais direta

Para o caso comum de "remover todo elemento que satisfaz uma condição", `removeIf` (disponível em qualquer `Collection`) encapsula esse mesmo padrão de `Iterator` + `remove()` numa única chamada, usando lambda:

```java
animes.removeIf(anime -> anime.equals("Bleach"));
```

`Iterator` manual continua sendo necessário quando a lógica de remoção é complexa demais para uma expressão lambda simples, ou quando outra ação precisa ser feita durante a iteração além de decidir remover (imprimir cada elemento processado, interromper a iteração no meio com `break`, etc.).

### ListIterator

Para listas especificamente, existe `ListIterator`, uma versão estendida de `Iterator` que permite percorrer em **ambas as direções** e também adicionar/substituir elementos durante a iteração.

```java
List<String> animes = new ArrayList<>(List.of("Naruto", "Bleach"));
ListIterator<String> it = animes.listIterator();

while (it.hasNext()) {
    String anime = it.next();
    if (anime.equals("Bleach")) {
        it.set("One Piece"); // substitui o elemento atual
    }
}

while (it.hasPrevious()) { // percorre de volta, do fim para o início
    System.out.println(it.previous());
}
```

| Método extra | Função |
| ------------- | ------ |
| `hasPrevious()` | Verifica se existe um elemento anterior |
| `previous()` | Retorna o elemento anterior e recua o cursor |
| `set(elemento)` | Substitui o último elemento retornado por `next()`/`previous()` |
| `add(elemento)` | Insere um elemento na posição atual |

## Comparable x Comparator

Duas interfaces diferentes, ambas usadas para definir como comparar objetos entre si, a diferença está em **onde** a lógica de comparação fica.

### Comparable

Define a ordenação **natural** de uma classe, implementada dentro da própria classe, através do método `compareTo()`. Cada classe só pode ter uma implementação de `Comparable`, ou seja, apenas uma ordenação "padrão".

```java
public class Anime implements Comparable<Anime> {
    private String nome;
    private int ano;

    @Override
    public int compareTo(Anime outro) {
        return this.nome.compareTo(outro.nome); // ordenação natural: por nome
    }
    // ...
}
```

```java
List<Anime> animes = new ArrayList<>();
// ...
animes.sort(null); // usa compareTo() -- ordena por nome
Collections.sort(animes); // equivalente, forma mais antiga
```

O retorno de `compareTo()` segue uma convenção:

| Retorno | Significado |
| ------- | ------------ |
| Negativo (-1) | `this` vem antes de `outro` |
| Zero (0) | `this` e `outro` são equivalentes, para fins de ordenação |
| Positivo (1) | `this` vem depois de `outro` |

### Comparator

Define uma ordenação **externa** à classe, criada separadamente, permitindo múltiplos critérios diferentes sem alterar a classe original.

```java
Comparator<Anime> porAno = Comparator.comparingInt(Anime::getAno);
Comparator<Anime> porNota = Comparator.comparingDouble(Anime::getNota);

animes.sort(porAno);
animes.sort(porNota);
```

`Comparator.comparing(...)` (e as variantes `comparingInt`/`comparingDouble`/`comparingLong`, para tipos primitivos) recebem uma *method reference* (`Classe::metodo`) apontando para o valor usado como critério de comparação.

**Ordem decrescente:**

```java
animes.sort(porNota.reversed());
```

**Combinando critérios (desempate):**

```java
Comparator<Anime> porAnoDepoisNome = Comparator.comparingInt(Anime::getAno)
                                                .thenComparing(Anime::getNome);
animes.sort(porAnoDepoisNome);
```

### Quando usar cada um

| | `Comparable` | `Comparator` |
| --- | --- | --- |
| Onde a lógica fica | Dentro da própria classe | Externa, criada onde for necessária |
| Quantidade por classe | Uma (a ordenação "natural") | Quantas forem necessárias |
| Método | `compareTo(outro)` | `compare(a, b)`, geralmente via `Comparator.comparing(...)` |
| Uso típico | Critério mais óbvio/esperado da classe | Critérios alternativos, pontuais |

Um `Comparator` também pode ser guardado como campo `static` dentro da própria classe, quando o critério for reutilizado com frequência em múltiplos lugares:

```java
public class Anime implements Comparable<Anime> {
    // ...
    public static final Comparator<Anime> POR_ANO = Comparator.comparingInt(Anime::getAno);
}
```

```java
animes.sort(Anime.POR_ANO);
```

### Impacto em TreeSet e TreeMap

`TreeSet` e `TreeMap` usam `compareTo()` (via `Comparable`) por padrão, ou um `Comparator` customizado passado no construtor. Nessas estruturas, a comparação também decide **igualdade** — se o critério usado retornar `0` para dois objetos diferentes, o segundo é tratado como duplicado e não é adicionado, sem erro ou aviso.

```java
Set<Anime> animes = new TreeSet<>(); // usa compareTo() -- ordena e desduplica por nome
Set<Anime> animesPorAno = new TreeSet<>(Comparator.comparingInt(Anime::getAno)); // usa este critério
```

Quando o critério de comparação é parcial (não cobre todos os campos relevantes da classe), objetos genuinamente diferentes podem ser descartados como duplicados. Nesses casos, o comparador precisa considerar campos suficientes para diferenciar os objetos, geralmente usando `.thenComparing(...)` como desempate.


## Classe Collections (utilitários)

Não confundir com a interface `Collection`, que é a raiz de `List`, `Set` e `Queue`. A classe `Collections`, com "s" no final, é um conjunto de métodos estáticos utilitários que operam sobre estruturas já existentes.

| Método | Função |
| ------ | ------ |
| `sort(lista)` | Ordena uma lista, usando `compareTo()` |
| `sort(lista, comparator)` | Ordena uma lista, usando um `Comparator` |
| `reverse(lista)` | Inverte a ordem dos elementos |
| `shuffle(lista)` | Embaralha os elementos aleatoriamente |
| `max(colecao)` | Retorna o maior elemento, segundo `compareTo()` |
| `min(colecao)` | Retorna o menor elemento, segundo `compareTo()` |
| `frequency(colecao, elemento)` | Conta quantas vezes um elemento aparece |
| `unmodifiableList(lista)` | Retorna uma versão somente leitura da lista |
| `emptyList()` | Retorna uma lista vazia e imutável |

```java
List<Integer> numeros = new ArrayList<>(List.of(5, 3, 8, 1));

Collections.sort(numeros);
System.out.println(numeros); // [1, 3, 5, 8]

Collections.reverse(numeros);
System.out.println(numeros); // [8, 5, 3, 1]

System.out.println(Collections.max(numeros)); // 8
System.out.println(Collections.min(numeros)); // 1
```

### Listas imutáveis

Além de `Collections.unmodifiableList(...)`, existem formas mais diretas de criar coleções imutáveis, (`List.of(...)`, `Set.of(...)`, `Map.of(...)`):

```java
List<Integer> lista = List.of(1, 2, 3);
lista.add(4); // UnsupportedOperationException, lista imutável
```

`Collections.unmodifiableList(lista)` cria uma "vista" somente leitura sobre uma lista mutável já existente. A lista original continua podendo ser alterada por quem tem a referência direta a ela.

```java
List<Integer> mutavel = new ArrayList<>(List.of(1, 2, 3));
List<Integer> somenteLeitura = Collections.unmodifiableList(mutavel);

somenteLeitura.add(4); // UnsupportedOperationException
mutavel.add(4); // funciona, altera a mesma estrutura por trás
System.out.println(somenteLeitura); // [1, 2, 3, 4], refletiu a mudança
```


## Generics

Generics permitem que uma classe, interface ou método sejam **parametrizados por tipo**, definindo qual tipo específico será usado apenas no momento em que forem instanciados ou chamados. O tipo é verificado pelo compilador, evitando erros que só apareceriam em tempo de execução.

```java
List<Integer> numeros = new ArrayList<>();
numeros.add(10);
numeros.add("texto"); // erro de compilação, não é permitido misturar tipos
```

Sem Generics, seria necessário usar `Object` como tipo genérico, perdendo a segurança de tipo e exigindo casting manual:

```java
List numeros = new ArrayList(); // sem parametrização, tipo bruto
numeros.add(10);
numeros.add("texto"); // compila normalmente, sem aviso

Integer valor = (Integer) numeros.get(0); // cast manual necessário
Integer valorErrado = (Integer) numeros.get(1); // ClassCastException em runtime
```

### Classes Genéricas

Uma classe pode declarar um ou mais parâmetros de tipo entre `< >`, geralmente representados por uma letra maiúscula única (convenção: `T` para tipo genérico, `K`/`V` para chave/valor, `E` para elemento).

```java
public class Caixa<T> {
    private T conteudo;

    public Caixa(T conteudo) {
        this.conteudo = conteudo;
    }

    public T getConteudo() {
        return conteudo;
    }
}
```

```java
Caixa<String> caixaTexto = new Caixa<>("Olá");
Caixa<Integer> caixaNumero = new Caixa<>(42);

System.out.println(caixaTexto.getConteudo());  // "Olá"
System.out.println(caixaNumero.getConteudo()); // 42
```

Uma classe genérica pode ter mais de um parâmetro de tipo:

```java
public class Par<K, V> {
    private K chave;
    private V valor;

    public Par(K chave, V valor) {
        this.chave = chave;
        this.valor = valor;
    }

    public K getChave() {
        return chave;
    }

    public V getValor() {
        return valor;
    }
}
```

```java
Par<String, Integer> idade = new Par<>("Ana", 25);
```

### Métodos Genéricos

Um método pode ter seu próprio parâmetro de tipo, independente da classe onde está declarado. O parâmetro de tipo é declarado antes do tipo de retorno.

```java
public class Utilitarios {
    public static <T> void imprimirTodos(List<T> lista) {
        for (T item : lista) {
            System.out.println(item);
        }
    }

    public static <T> T primeiroElemento(List<T> lista) {
        return lista.get(0);
    }
}
```

```java
List<String> nomes = List.of("Ana", "Bruno");
Utilitarios.imprimirTodos(nomes);

String primeiro = Utilitarios.primeiroElemento(nomes);
```

O tipo `T` é inferido automaticamente pelo compilador a partir do argumento passado, sem necessidade de especificar explicitamente.

### Bounded Types (restringindo o tipo aceito)

É possível restringir quais tipos um parâmetro genérico aceita, usando `extends` (mesmo em interfaces, não só classes).

```java
public class Caixa<T extends Number> { // só aceita Number ou subclasses (Integer, Double, etc.)
    private T conteudo;

    public Caixa(T conteudo) {
        this.conteudo = conteudo;
    }

    public double dobro() {
        return conteudo.doubleValue() * 2; // método de Number, disponível por causa do bound
    }
}
```

```java
Caixa<Integer> caixaInteiro = new Caixa<>(10); // válido
Caixa<String> caixaTexto = new Caixa<>("Oi");  // erro de compilação, String não estende Number
```

Sem o `extends Number`, o compilador não permitiria chamar `.doubleValue()` dentro da classe, já que um `T` genérico sem restrição só garante os métodos de `Object`.

### Wildcards

O caractere `?` representa um tipo desconhecido, usado principalmente como parâmetro de método quando o tipo exato não importa, apenas que seja compatível com alguma restrição.

```java
public static void imprimirLista(List<?> lista) { // aceita List de qualquer tipo
    for (Object item : lista) {
        System.out.println(item);
    }
}
```

```java
imprimirLista(List.of(1, 2, 3));       // funciona
imprimirLista(List.of("a", "b", "c")); // também funciona
```

**Wildcard limitado superiormente** (`? extends Tipo`), aceita o tipo especificado ou qualquer subclasse dele. Usado quando o método só lê da coleção.

```java
public static double somarTodos(List<? extends Number> lista) {
    double soma = 0;
    for (Number numero : lista) {
        soma += numero.doubleValue();
    }
    return soma;
}
```

```java
somarTodos(List.of(1, 2, 3));       // List<Integer>, aceito
somarTodos(List.of(1.5, 2.5));      // List<Double>, aceito
```

**Wildcard limitado inferiormente** (`? super Tipo`), aceita o tipo especificado ou qualquer superclasse dele. Usado quando o método escreve na coleção.

```java
public static void adicionarInteiros(List<? super Integer> lista) {
    lista.add(1);
    lista.add(2);
}
```

```java
List<Number> numeros = new ArrayList<>();
adicionarInteiros(numeros); // Number é superclasse de Integer, aceito
```

### Generics em Collections

Toda a estrutura vista até aqui (`List<T>`, `Map<K, V>`, `Set<T>`) já é construída usando Generics. O framework Collections foi, historicamente, a principal motivação para a introdução de Generics na linguagem, permitindo eliminar o uso de `Object` e o casting manual que existia nas versões anteriores do Java.