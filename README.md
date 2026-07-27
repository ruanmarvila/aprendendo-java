# Aprendendo Java (Vindo do Python)

Este repositório reúne minhas anotações, exemplos e exercícios enquanto estudo Java. O conteúdo é atualizado enquanto avanço nos estudos, então a organização acompanha a minha evolução na linguagem.

Meu objetivo é registrar a evolução do aprendizado, criar um material de consulta e comparar conceitos que já conheço em Python com a forma como eles funcionam em Java.

> **Observação:** Este material utiliza o **Java 25**. Alguns recursos apresentados (como Switch Expressions e Pattern Matching) podem não estar disponíveis em versões mais antigas da linguagem.

# Índice
- [Progresso](#progresso)
- [Básico](#básico)
  - [Hello World](#hello-world)
  - [Estrutura](#estrutura-de-um-programa-java)
  - [Nomenclatura](#nomenclatura)
  - [Variáveis e Constantes](#variáveis-e-constantes)
    - [Variáveis](#variáveis)
    - [Constantes](#constantes)
  - [Tipos Primitivos](#tipos-primitivos)
  - [Casting](#casting)
  - [Strings](#strings)
  - [Null](#null)
  - [Inferência de Tipo](#inferência-de-tipo-var)
  - [Operadores](#operadores)
    - [Operadores Aritméticos](#operadores-aritméticos)
    - [Operadores Relacionais](#operadores-relacionais)
    - [Operadores Lógicos](#operadores-lógicos)
      - [Funcionamento](#como-cada-operador-funciona)
      - [Curto-circuito](#curto-circuito-short-circuit)
  - [Classe Math](#classe-math)
  - [Classe Random](#classe-random)
  - [Entrada de Dados](#entrada-de-dados)
  - [Saída de Dados](#saída-de-dados)
  - [Comentários](#comentários)
    - [Atalhos](#atalhos-para-comentar)
  - [Condicionais](#condicionais-if-else-if-else-switch-case)
    - [If, Else e Else-if](#if-else-e-else-if)
    - [Operador Ternário](#operador-ternário)
    - [Switch](#switch)
  - [Loops](#loops)
    - [For e For-each](#for-e-for-each)
    - [While e Do-While](#while-e-do-while)
    - [Controle de Repetição](#controle-de-repetição-break-e-continue)
  - [Métodos](#métodos)
    - [Métodos com Retorno](#método-com-retorno)
    - [Métodos void](#método-sem-retorno-void)
    - [Métodos static](#método-static)
    - [Sobrecarga de Métodos](#sobrecarga-de-métodos-overload)
    - [Parâmetros x Argumentos](#parâmetro-x-argumento)
  - [Arrays](#arrays)
    - [Arrays](#arrays-com-tamanho-fixo)
    - [Matrizes](#arrays-multidimensionados)
    - [ArrayList](#arrays-dinâmicos)
    - [Diferenças](#diferenças-entre-array-e-arraylist)

# Progresso
- [x] Conceitos Básicos
  - [x] Hello World
  - [x] Estrutura
  - [x] Variáveis
  - [x] Tipos primitivos
  - [x] Casting
  - [x] String
  - [x] Operadores
  - [x] Entrada
  - [x] Condicionais
  - [x] Loops
  - [x] Métodos
  - [x] Arrays
- [ ] POO
  - [ ] Classes
  - [ ] Objetos
  - [ ] Encapsulamento
  - [ ] Construtores
  - [ ] Herança
  - [ ] Polimorfismo
  - [ ] Classes Abstratas
  - [ ] Interfaces
  - [ ] Exceções
- [ ] Java Moderno
  - [ ] Collections
  - [ ] Generics
  - [ ] Lambdas
  - [ ] Streams
  - [ ] Records
  - [ ] Maven
  - [ ] JUnit
- [ ] Spring Boot

---


# Básico

O Java é uma linguagem que possui forte integração com o paradigma da **Programação Orientada a Objetos (POO)**.

## Hello World

Em Java, para exibir algo no console, usamos:

```java
System.out.println("Hello, World!");
```

- o `println()` serve para quebrar a linha, porém existe o `print()`.

  ```java
  System.out.println("E");
  System.out.println("S");
  /* Saída:
  E
  S
  */

  System.out.print("E");
  System.out.print("S");
  // Saída: ES
  ```

## Estrutura de um programa Java

Um programa Java normalmente possui uma classe com o método `main`, que é o ponto de entrada da aplicação.

```java
public class Main { // Define uma classe
    public static void main(String[] args) { // Porta de entrada do programa
        System.out.println("Hello, World!"); // Imprime no terminal
    }
}
```

## Nomenclatura

- **camelCase:** Começa com letra minúscula, e depois maiúscula para a primeira letra de cada palavra $\rightarrow$ Variáveis e Métodos
  - primeiroNome
  - calcularTotal
  - taxaDeJuros

- **UPPER_SNAKE_CASE:** Todas as palavras em maiúsculo com o underline separando-as $\rightarrow$ Constantes
  - VALOR_MAXIMO
  - PONTUACAO_MINIMA
  - MAPA_DE_ERROS

- **PascalCase:** Toda palavra começa com a primeira letra maiúscula $\rightarrow$ Classes
  - Main
  - Pessoa
  - UsuarioRepository

- **Gerais:**
  - Nomes podem conter letras, dígitos, sublinhados e sinais de dólar.
  - Nomes devem começar com uma letra, um sublinhado `_`, ou um sinal de dólar `$`.

    > **Observação:** Apesar de permitido, iniciar identificadores com `_` ou `$` não é recomendado em código Java moderno.

## Variáveis e Constantes

O Java segue um padrão para a inicialização de variáveis e constantes.

### Variáveis

Variáveis armazenam valores que podem ser alterados durante o programa.

- Sintaxe: `tipo nome = valor;`

  ```java
  int numero = 10;
  ```

### Constantes

Diferente das variáveis, constantes não podem ter o valor alterado após sua inicialização. Para isso, elas usam o modificador `final` para indicar ao compilador que aquilo se trata de uma constante.

- Sintaxe: `final tipo NOME = valor;`

  ```java
  final int VALOR_MAXIMO = 100;
  ```


## Tipos Primitivos

Java possui 8 tipos primitivos:

| Tipo | Tamanho | Exemplo |
| ---- | ------- | ------- |
| byte | 8 bits | byte idade = 20; |
| short | 16 bits | short ano = 2026; |
| int | 32 bits | int numero = 100; |
| long | 64 bits | long populacao = 8000000L; |
| float | 32 bits | float altura = 1.75f; |
| double | 64 bits | double salario = 2500.50; |
| char | 16 bits | char letra = 'A'; |
| boolean | Não definido | boolean ativo = true; |

Além dos tipos primitivos, o Java possui classes equivalentes chamadas Wrappers.

- int -> Integer
- double -> Double
- char -> Character
- boolean -> Boolean


## Casting

Casting é a conversão de um tipo para o outro, tanto de forma implícita e explícita.

- int para double (acontece de forma implícita), sem perda de dados

  ```java
  int numero = 5;
  double decimal = numero; // 5.0
  ```

- double pra int (acontece de forma explícita), a parte decimal é descartada

  ```java
  double decimal = 10.5;
  int numero = (int) decimal; // 10
  ```

## Strings

Em Java, `String` não é um tipo primitivo, ela é uma classe, ou seja, um objeto que possui métodos.

1. Manipulação e Métodos:

    ```java
    String texto = "  Dev Java  ";

    texto.toUpperCase();         // "  DEV JAVA  "
    texto.toLowerCase();         // "  dev java  "
    texto.trim();                // "Dev Java" -> remove espaços das pontas
    texto.length();              // 12 -> tamanho
    texto.contains("Java");      // true
    texto.replace("Java", "C#"); // "  Dev C#  "
    ```

    1.1 **`String` é imutável**

    ```java
    String texto = "Java";

    texto.toLowerCase();
    System.out.println(texto); // Java

    // O certo seria
    texto = texto.toLowerCase();
    System.out.println(texto); // java
    ```

    1.2 Checagem de `String` vazia

    - `isEmpty()` retorna `true` quando a `String` possui comprimento igual a zero.

      ```java
      String texto1 = "";
      String texto2 = "Java";

      System.out.println(texto1.isEmpty()); // true
      System.out.println(texto2.isEmpty()); // false
      ```
    
    - `isBlank()` retorna `true` quando a `String` está vazia ou contém apenas espaços em branco.

      ```java
      String texto1 = "";
      String texto2 = "   ";
      String texto3 = "Java";

      System.out.println(texto1.isBlank()); // true
      System.out.println(texto2.isBlank()); // true
      System.out.println(texto3.isBlank()); // false
      ```

      | Método | `""` | `"   "` | `"Java"` |
      | ------ | :--: | :-----: | :------: |
      | `isEmpty()` | `true` | `false` | `false` |
      | `isBlank()` | `true` |   `true` | `false` |

2. Converter para String:
- Para converter um tipo para String, usa-se `String.valueOf()`.

    ```java
    // int -> String
    String texto = String.valueOf(200); // "200"

    // double -> String
    String texto2 = String.valueOf(12.7); // "12.7"

    // boolean -> String
    String texto3 = String.valueOf(true); // "true"
    ```

3. Converter String para tipo:
- Para converter uma String para um tipo primitivo nós usamo um Wrapper do tipo necessário.

    ```java
    // String -> int
    int numero = Integer.parseInt("20"); // 20

    // String -> double
    double decimal = Double.parseDouble("6.9"); // 6.9

    // String -> boolean
    boolean booleano = Boolean.parseBoolean("true"); // true
    ```

    > **Observação:** O `parseBoolean()` é case-insensitive, logo, "true" pode ser escrito todo em minúsculo, maiúsculo ou captalizado, o resultado será `true`, e qualquer valor que não seja `true` será `false`, ou seja, se alguém colocar "verdadeiro" o resultado será `false`.

## Null

Em Java, `null` representa a ausência de um objeto.

Diferentes dos tipos primitivos, objetos podem não apontar para nenhuma instância.

```java
String nome = null;

System.out.println(nome); // null

int numero = null; // erro de compilação
```

## Inferência de tipo (var)

Quando utilizamos o `var` em vez de um tipo primitivo numa variável, o compilador faz uma inferência do tipo baseado no valor.

```java
var numero = 2; // int
var decimal = 2.5; // double

var texto; // erro, pois o compilador não será capaz de fazer a inferência do tipo
numero = "texto"; // erro, "var" não significa que a variável é dinâmica, "numero" é um "int", ele só vai aceitar valores "int"
```

> O Java é uma linguagem com tipagem estática, diferente do Python que é dinamicamente tipado

## Operadores

### Operadores Aritméticos

O Java tem cinco operadores aritméticos principais.

| Símbolo | Significado | Exemplo |
| ------- | ----------- | ------- |
| `+` | Soma e concatenação para String | `5 + 2` $\rightarrow$ `7`<br>`"A" + 1` $\rightarrow$ `"A1"`|
| `-` | Subtração | `5 - 2` $\rightarrow$ `3` |
| `*` | Multiplicação | `5 * 2` $\rightarrow$ `10` |
| `/` | Divisão | `5 / 2` $\rightarrow$ `2` |
| `%` | Resto da divisão (módulo) | `5 % 2` $\rightarrow$ `1` |

#### Observações

1. Divisão Inteira vs Decimal<br>
O resultado da divisão (`/`), depende do tipo dos operadores:
- Se ambos os operadores forem inteiros (`byte`, `short`, `int`, `long`), a divisão é inteira (a parte decimal é truncada);
  - Exemplo: `3 / 2` resulta em `1`.
- Se pelo menos um for (`float` ou `double`), o resultado será decimal.
  - Exemplo: `3.0 / 2` ou `3 / 2.0` resulta em `1.5`.

2. Operadores Compostos e Incremento

    | Operador | Equivalente |
    | -------- | ----------- |
    | `x += y` | `x = x + y` |
    | `x -= y` | `x = x - y` |
    | `x *= y` | `x = x * y` |
    | `x /= y` | `x = x / y` |
    | `x %= y` | `x = x % y` |
    | `x++` / `++x` | `x = x + 1` |
    | `x--` / `--x` | `x = x - 1` |

- Diferença entre `x++` e `++x`
  - Pós-incremento: Atribui o valor de x e depois incrementa o valor em x

    ```java
    int x = 5;
    int y = x++;

    System.out.println(x); // 6
    System.out.println(y); // 5
    ```
  - Pré-incremento: Incrementa o valor em x e depois atribui o valor de x

      ```java
      int x = 5;
      int y = ++x;

      System.out.println(x); // 6
      System.out.println(y); // 6
      ```

### Operadores Relacionais

Os operadores relacionais comparam dois valores e resulta em um `boolean`.

| Operador | Significado | Exemplo | Resultado |
| -------- | ----------- | ------- | --------- |
| `==` | Igual a | 5 == 5 | `true` |
| `!=` | Diferente de | 3 != 3 | `false` |
| `>` | Maior que | 2 > 1 | `true` |
| `<` | Menor que | 4 < 3 | `false` |
| `>=` | Maior ou igual a | 6 >= 6 | `true` |
| `<=` | Menor ou igual a | 8 <= 5 | `false` |

- O operador "igual a" (`==`) não funciona para comparar `Strings`, no lugar usamos o `.equals()`

  ```java
  String a = new String("Oi");
  String b = new String("Oi");

  System.out.println(a == b); // false
  System.out.println(a.equals(b)); // true
  ```

### Operadores Lógicos

Os operadores lógicos são utilizados para combinar ou inverter expressões que resultam em um `boolean`.

| Operador | Significado | Exemplo | Resultado |
| -------- | ----------- | ------- | --------- |
| `&&` | E (AND) | `true && false` | `false` |
| `\|\|` | Ou (OR) | `true \|\| false` | `true` |
| `!` | Não (NOT) | `!true` | `false` |

- Exemplos:

    ```java
    int idade = 20;
    boolean possuiCarteira = true;

    System.out.println(idade >= 18 && possuiCarteira); // true
    System.out.println(idade >= 18 || possuiCarteira); // true
    System.out.println(!possuiCarteira); // false
    ```

#### Como cada operador funciona

- `&&` (E): retorna `true` apenas se **ambas** as condições forem verdadeiras.
  ```java
  5 > 2 && 10 > 5 // true
  5 > 2 && 10 < 5 // false
  ```

- `||` (OU): retorna `true` se **pelo menos uma** das condições for verdadeira.
  ```java
  5 > 2 || 10 < 5 // true
  5 < 2 || 10 < 5 // false
  ```

- `!` (NÃO): inverte o valor de uma expressão booleana.
  ```java
  !true  // false
  !false // true
  ```

#### Curto-circuito (Short-circuit)

Os operadores `&&` e `||` utilizam **avaliação de curto-circuito**, ou seja, a segunda expressão só é avaliada quando necessário.

```java
int x = 10;

System.out.println(x > 5 || x++ > 10);
System.out.println(x); // 10
```

Como a primeira condição já é `true`, a segunda não é executada.


```java
int x = 10;

System.out.println(x < 5 && x++ > 10);
System.out.println(x); // 10
```

Como a primeira condição já é `false`, a segunda também não é executada.


## Classe Math

Em Java, nós temos uma classe para operações matemáticas chamada `Math` do pacote `java.lang` que é importado automaticamente

| Método | Descrição | Exemplo |
| ------ | --------- | ------- |
| `Math.abs(x)` | Retorna o valor absoluto | `Math.abs(-6)` $\rightarrow$ `6` |
| `Math.max(x, y)` | Retorna o maior valor | `Math.max(5, 12)` $\rightarrow$ `12` |
| `Math.min(x, y)` | Retorna o menor valor | `Math.min(5, 12)` $\rightarrow$ `5` |
| `Math.sqrt(x)` | Retorna a raiz quadrada | `Math.sqrt(25)` $\rightarrow$ `5.0` |
| `Math.cbrt(x)` | Retorna a raiz cúbica | `Math.cbrt(27)` $\rightarrow$ `3.0` |
| `Math.pow(x, y)` | Retorna a potência | `Math.pow(2, 3)` $\rightarrow$ `8.0` |
| `Math.round(x)` | Arredonda para o inteiro mais próximo | `Math.round(5.8)` $\rightarrow$ `6` |
| `Math.floor(x)` | Arredonda para baixo | `Math.floor(3.5)` $\rightarrow$ `3` |
| `Math.ceil(x)` | Arredonda para cima | `Math.ceil(4.1)` $\rightarrow$ `5` |
| `Math.PI` | Retorna o valor de PI | `Math.PI` $\rightarrow$ `3.14159...` |
| `Math.E` | Retorna o número de Euler | `Math.E` $\rightarrow$ `2.71828...` |

  > **Observação:** Essa tabela não possui todos os métodos de `Math`, ainda tem métodos relacionado à trigonometria, logaritmos e etc.


## Classe Random

Em Java, nós usamos a classe `Random` importada do pacote `java.util` para gerar valores aleatórios

Para usar os métodos, precisamos criar um objeto `Random`

```java
import java.util.Random;

Random aleatorio = new Random();

int numero =  aleatorio.nextInt();
```

| Método | Descrição | Exemplo |
| ------ | --------- | ------- |
| `nextInt()` | Gera um número inteiro | `-200` |
| `nextInt(limite)` | Gera um número entre 0 e (limite - 1)| `nextInt(10)` $\rightarrow$ `7` |
| `nextDouble()` | Gera um decimal entre 0.0 e 1.0 | `0.5329` |
| `nextFloat()` | Gera um float entre 0.0 e 1.0 | `0.69` |
| `nextBoolean()` | Gera `true` ou `false` | `false` |

- Observações: <br>

  Você consegue manipular o `nextInt(limite)` para gerar intervalos que não comecem em zero

  ```java
  Random random = new Random();

  int numero = random.nextInt(10) + 1; // Isso gera um número de 1 a 10
  int numeroMaior = random.nextInt(51) + 50; // Isso gera um número de 50 a 100
  ``` 

  O `nextInt(51)` vai gerar um número de 0 a 50, e o `+ 50` vai deslocar para 50 a 100


### ThreadLocalRandom

O `ThreadLocalRandom` é uma outra forma de gerar números aleatórios em Java, ela é do pacote `java.util.concurrent`

Diferente de `Random`, nós não precisamos criar um objeto, podemos usar o `ThreadLocalRandom` diretamente

```java
import java.util.concurrent.ThreadLocalRandom;

int numero = ThreadLocalRandom.current().nextInt();
```

O `ThreadLocalRandom` permite definir um intervalo

```java
int dado = ThreadLocalRandom.current().nextInt(1, 7); // Gera um número de 1 a 6
double nota = ThreadLocalRandom.current().nextDouble(0, 11); // Gera um número de 0.0 a 10.0
```

> `Random` é suficiente para a maioria dos programas. Já `ThreadLocalRandom` é otimizado para aplicações concorrentes


## Entrada de Dados

Para lermos dados digitados pelo usuário no console, precisamos importar a classe `Scanner` do pacote `java.util` e instânciá-la utilizando `System.in`

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // O System.in representa a entrada padrão

        System.out.print("Digite o seu nome: ");
        String nome = scanner.nextLine(); // nextLine() lê uma String

        System.out.print("Digite sua idade: ");
        int idade = scanner.nextInt(); // nextInt() lê um int

        System.out.println("Olá, " + nome + " você tem " + idade + " anos");

        scanner.close(); // O Scanner deve ser fechado no final
    }
}
```

- Observação: <br>
  Ao usar `nextInt()` seguido de `nextLine()`, é necessário consumir a quebra de linha restante com um `scanner.nextLine()`,

  ```java
  int idade = scanner.nextInt();
  scanner.nextLine(); // consome o "\n" que ficou no buffer
  String nome = scanner.nextLine();
  ```


## Saída de Dados

Para mostramos algo no console, podemos usar diferentes variações do comando `print`

- O `print()` imprime a mensagem no console, uma na frente da outra

  ```java
  // Damos um espaço no final da mensagem para separá-las na exibição do console
  System.out.print("ES ");
  System.out.print("MG ");
  System.out.print("RJ ");
  System.out.print("SP ");
  ```
  Saída:
  ```
  ES MG RJ SP
  ```

- O `println()` faz uma quebra de linha após imprimir a mensagem no console

  ```java
  System.out.println("ES");
  System.out.println("MG");
  System.out.println("RJ");
  System.out.println("SP");
  ```
  Saída:
  ```
  ES
  MG
  RJ
  SP
  ```

- O `printf()` é usado para formatação de variáveis na mensagem

  ```java
  String nome = "Maria";
  int idade = 22;
  double altura = 1.64;

  System.out.printf("A %s fez %d anos e tem %.2f de altura.", nome, idade, altura);
  ```
  Saída:
  ```
  A Maria fez 22 anos e tem 1.64 de altura.
  ```

  Principais marcadores utilizados:
  - `%s` $\rightarrow$ texto (`String`)
  - `%d` $\rightarrow$ números inteiros (`int`)
  - `%f` $\rightarrow$ números decimais (`double`, `float`)
  - `%.2f` $\rightarrow$ números decimais com duas casas após a vírgula


## Comentários

Em Java, para fazermos comentários no códigos, utilizamos `//` para comentários de uma linha e `/* */` para comentários em blocos

```java
// Isso é um comentário de uma linha, ele comenta a linha inteira a partir da //

int numero = 5; // O comentário precisa ser feito depois do ";" para não quebrar o código

/*
Isso é 
um comentário 
em bloco
*/
```

### Atalhos para comentar
- Comentário com `//`
  - Windows / Linux: `Ctrl` + `;` (ou `Ctrl` + `/`)
  - Mac: `Cmd` + `;` (ou `Cmd` + `/`)
    - Para comentar uma linha coloque o cursor em qualquer parte da linha
    - Para comentar várias linhas selecione todas as linhas que deseja comentar

- Comentário com `/* */`
  - Windows / Linux: `Shift` + `Alt` + `A`
  - Mac: `Shift` + `Option` + `A`
    - Para comentário em bloco selecione todas as linhas desejadas


## Condicionais (if, else-if, else, switch-case)

As estruturas condicionais permitem que o programa execute diferentes blocos de código dependendo do resultado de uma condição.

### if, else e else-if

O `if` executa um bloco de código **apenas** se a condição for `true`.

```java
int idade = 20;

if (idade >= 18) {
    System.out.println("Maior de idade");
}
```

Quando existe uma segunda possibilidade, utilizamos o `else`.

```java
int idade = 16;

if (idade >= 18) {
    System.out.println("Maior de idade");
} else {
    System.out.println("Menor de idade");
}
```

Quando existem várias condições diferentes, utilizamos o `else if`.

```java
int nota = 60;

if (nota >= 90) {
    System.out.println("Excelente");
} else if (nota >= 70) {
    System.out.println("Aprovado");
} else if (nota >= 60) {
    System.out.println("Recuperação");
} else {
    System.out.println("Reprovado");
}
```

- Assim que uma condição for verdadeira, as demais não serão avaliadas.

### Operador Ternário

Quando queremos escolher entre apenas dois valores, podemos utilizar o operador ternário (`? :`).

Sintaxe: `condicao ? valorSeTrue : valorSeFalse;`

Exemplo:

```java
int idade = 20;

String mensagem = idade >= 18 ? "Maior de idade" : "Menor de idade";

System.out.println(mensagem); // Maior de idade
```

### switch

O `switch` é utilizado quando precisamos comparar uma mesma variável com vários valores diferentes.

```java
int dia = 3;

switch (dia) {
    case 1:
        System.out.println("Domingo");
        break;

    case 2:
        System.out.println("Segunda-feira");
        break;

    case 3:
        System.out.println("Terça-feira");
        break;

    default:
        System.out.println("Dia inválido");
}
```

- O `break` encerra o `switch`.
- Sem o `break`, o programa continua executando os próximos casos.

Exemplo sem `break`:

```java
int numero = 1;

switch (numero) {
    case 1:
        System.out.println("Um");

    case 2:
        System.out.println("Dois");

    case 3:
        System.out.println("Três");
}

/*Saída:
Um
Dois
Três
*/
```

### switch com múltiplos casos

É possível executar o mesmo bloco para vários valores.

```java
char vogal = 'A';

switch (vogal) {
    case 'A':
    case 'E':
    case 'I':
    case 'O':
    case 'U':
        System.out.println("É uma vogal");
        break;

    default:
        System.out.println("Não é uma vogal");
}
```

### Switch Expression (Java 14+)

Nas versões mais recentes do Java, o `switch` pode ser utilizado como uma expressão.

```java
int dia = 2;

String nomeDia = switch (dia) {
    case 1 -> "Domingo";
    case 2 -> "Segunda-feira";
    case 3 -> "Terça-feira";
    default -> "Dia inválido";
};

System.out.println(nomeDia); // Segunda-feira
```

Esse formato elimina a necessidade do `break` e deixa o código mais limpo.


### Pattern Matching no switch (Java 21+)

Nas versões mais recentes do Java, o `switch` também pode realizar verificações de tipo, dispensando o uso de `instanceof` e de casts.

```java
Object objeto = "Olá";

switch (objeto) {
    case String texto -> System.out.println("String: " + texto);
    case Integer numero -> System.out.println("Inteiro: " + numero);
    case null -> System.out.println("Valor nulo");
    default -> System.out.println("Tipo desconhecido");
}
```

Nesse exemplo, caso `objeto` seja uma `String`, a variável `texto` já será criada com o tipo correto, não sendo necessário fazer cast.

### Observações

- As condições do `if` sempre devem resultar em um `boolean`.
- Diferente do Python, Java exige que a condição seja explicitamente booleana.

  ```python
  if usuario:
    # bloco de código
  ```
  ```java
  if (usuario != null) {
    // bloco de código
  }
  ```

  Errado:

  ```java
  int numero = 10;

  if (numero) { // Erro
      System.out.println("Olá");
  }

  int numero = 10;

  if (numero > 0) { // Certo
      System.out.println("Olá");
  }
  ```

- O `switch` funciona com tipos como `byte`, `short`, `int`, `char`, `String` e `enum`, mas não aceita `boolean`, `float`, `double` ou `long`.


## Loops

Os laços de repetição servem para executar um bloco de código várias vezes enquanto uma condição for verdadeira

O Java possui 4  laços de repetiçoes principais: `for`, `for-each`, `while` e `do-while`

### For e For-Each

Utilizamos `for` quando sabemos a quantidade exata de repetições que o laço deve fazer

- Sintaxe: `for (inicialização; condição; incremento/decremento) {...}`

  ```java
  for (int i = 0; i < 5; i++) {
    System.out.println("Índice: " + i); // imprime de 0 a 4
  }
  ```

Utilizamos `for-each` para percorremos arrays e coleções como (`List`, `Set`, etc)

- Sintaxe: `for (tipo elemento : array) {...}`

  ```java
  String[] frutas = {"Maça", "Banana", "Laranja"};

  for (String fruta : frutas) {
    System.out.println("Fruta: " + fruta);
  }
  ```

### While e Do-While

Utilizamos `while` quando não sabemos quantas repetições serão necessárias

O `while` avalia a condição **antes** de executar o bloco de código. Se a condição for false logo no início, o bloco não será executado nenhuma vez

- Sintaxe: `while (condicao) {...}`

  ```java
  int count = 1;

  while (count <= 10) {
    System.out.print(count + " "); // 1 2 3 4 5 6 7 8 9 10
    count++;
  }
  ```

Diferente do `while` o `do-while` avalia a condição **depois** de executar o bloco de código. Isso faz com que o bloco sempre seja executado no mínimo 1 vez.

- Sintaxe: `do {...} while (condicao);`

  ```java
  int opcao = 0;

  do {
    System.out.println("Escolha uma opção: ");
    System.out.println("1 - Continuar | 0 - Sair");
    // Teria um Scanner para ler a resposta
  } while (opcao != 0);
  ```

### Controle de Repetição: Break e Continue

O `break` e `continue` são usados para controlar a execução de laços de repetições, geralmente combinados com um `if` para agirem em momentos específicos

- `break` serve para você encerrar o laço. A execução vai pra primeira linha depois do loop
- `continue` serve para você pular a execução atual do loop e ir direto pra próxima

  ```java
  // Exemplo com break
  for (int i = 1; i <= 10; i++) {
      if (i == 5) {
          break; // Sai do loop quando 'i' for igual a 5
      }
      System.out.print(i + " "); // 1 2 3 4
  }

  // Exemplo com continue
  for (int i = 1; i <= 5; i++) {
      if (i == 3) {
          continue; // Pula a impressão quando 'i' for 3
      }
      System.out.print(i + " "); // Saída: 1 2 4 5
  }
  ```

## Métodos

Métodos, também conhecidos como funções em algumas linguagens, são blocos de códigos que executam uma função específica. Eles ajudam a organizar o programa e evitar repetição de código, (*"Don't repeat yourself" - DRY*)

- Estrutura de um método: `modificador tipoDeRetorno nomeDoMetodo(tipo parametro) {...}`

  ```java
  public int somar(int x, int y) {
    return x + y;
  }
  ```

  > Observação: um método pode ter vários parâmetros ou nenhum

### Método com retorno

Retorna um valor para quem chamou

```java
public double calcularMedia(double nota1, double nota2) {
    return (nota1 + nota2) / 2;
  }

double media = calcularMedia(8, 6);

System.out.println("Média: " + media); // 7
```

  > Observação: o retorno tem que ser compatível com o tipo declarado no método

### Método sem retorno (`void`)

Quando o método executa uma ação e não devolver nenhum valor

```java
public void bemVindo() {
  System.out.println("Bem-vindo!");
}

bemVindo(); // Bem-vindo!
```

### Método `static`

Um método `static` pertence a classe e pode ser chamado sem criar um objeto

```java
public class Calculadora {

  public static int multiplicar(int x, int y) {
      return x * y;
  }
  
  public static void main(String[] args) {
    int resultado =  Calculadora.multiplicar(5, 8);

    System.out.println(resultado); // 40
  }

}
```

### Método de Instância

Precisa criar um objeto da classe

```java
public class Pessoa {

  public void falar() {
      System.out.println("Olá!");
  }

  public static void main(String[] args) {
    Pessoa pessoa = new Pessoa();
    pessoa.falar(); // Olá!
  }

}
```

### Sobrecarga de Métodos (Overload)

Em Java, é possível ter métodos com o mesmo nome, mas parâmetros diferentes

O Java escolhe qual método usar baseado no argumento

```java
public class Calculadora {

  public int somar(int a, int b) {
      return a + b;
  }

  public double somar(double a, double b) {
      return a + b;
  }

}
```

### Parâmetro x Argumento

Os dois termos parecem sinônimos, mas cada um tem seu momento

- Parâmetro é o nome que você dá à entrada quando define o método (é uma variável reservada para receber um valor)
- Argumento é o valor de verdade que você passa quando chama o método

  ```java
  public class Main {
    public static void saudar(String nome) { // nome é o parâmetro
      System.out.println("Bom dia, " + nome + "!");
    }

    public static void main(String[] args) {
      saudar("Victor"); // Victor é o argumento
      // Saída: Bom dia, Victor!
    }
  }
  ```

## Arrays

Arrays são uma forma de guardar mais de um valor em uma mesma variável ou constante.

### Arrays com tamanho fixo

Podemos instanciar um array de duas formas diferentes, a primeira é indicando somente o tamanho dele 

- Sintaxe: `tipo[] nome = new tipo[x];`

  ```java
  // Arrays de inteiros
  int[] numeros = new int[5]; // {0, 0, 0, 0, 0}
  ```

A segunda forma é atribuindo valor na criação do array

- Sintaxe: `tipo[] nome = {...};`

  ```java
  // Array de String
  String[] nomes = {"Ana", "Luca", "Bruno"};
  ```

- Observação: <br>
  Quando criamos um array indicando só o tamanho, o Java atribui um valor padrão para cada índice

  | Tipo | Valor Padrão |
  | ---- | ------------ |
  | `byte`, `short` e `int` | 0 |
  | `long` | 0L |
  | `float` | 0.0f |
  | `double` | 0.0 |
  | `char` | '\u0000' |
  | `boolean` | `false` |
  | Objeto(`String`, `Integer`) | `null` |


- Atribuindo valores: <br>
  Em Java, usamos o índice para atribuimos um valor à posição indicada

  ```java
  int[] numeros = new int[5];

  numeros[1] = 2;
  numeros[3] = 4;

  for (int n : numeros) {
    System.out.print(n + " "); // 0, 2, 0, 4, 0
  }
  ```

- Métodos: <br>
  Os arrays não possuem métodos próprios, porém podemos usar a classe `Arrays` do pacote `java.util` para usar métodos de manipulação de dados

  | Método | Função |
  | ------ | ------ |
  | `sort()` | Ordena o array |
  | `toString()` | Exibe o conteúdo do array |
  | `binarySearch()` | Pesquisa em um array ordenado |
  | `equals()` | Compara o conteúdo de dois arrays |
  | `fill()` | Preenche todas as posições |
  | `copyOf()` | Copia um array |
  | `copyOfRange()` | Copia parte de um array |
  | `deepToString()` | Exibe arrays multidimensionais |
  | `deepEquals()` | Compara arrays multidimensionais |
  | `asList()` | Converte um array em `List` |


### Arrays Multidimensionados

Arrays multidimensionados, ou matrizes, são estruturas de dados que organizam elementos em duas ou mais dimensões

- Sintaxe: 
  - `tipo[][] nome = new tipo[x][y]`
  - `tipo[][] nome = {...}`
    ```java
    int[][] matriz = new int[2][3];
    /*
    [0, 0, 0]
    [0, 0, 0]
    */
    ```

    ```java
    int[][] matriz = {
      {1, 2, 3},
      {4, 5, 6}
    };
    ```

- Atribuindo valores: <br>
  Nas matrizes, usamos o índice da linha e da coluna `matriz[linha][coluna] = x` para atribuir um elemento

  ```java
  int[][] matriz =  new int[2][2];

  matriz[0][0] = 1;
  matriz[1][1] = 2;
  /*
  [1, 0]
  [0, 2]
  */
  ```


### Arrays dinâmicos

Para criar arrays com tamanho dinâmico, precisamos importar as classes `List` e `ArrayList` do pacote `java.util`

```java
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    List<Integer> numeros = new ArrayList<>(); // [] -> vazio
  }
}
```

- Atribuindo valores: <br>
  Usamos o método `.add()` para adicionar um elemento no final do array

  ```java
  numeros.add(5);
  numeros.add(3);
  numeros.add(18);

  // O array "numeros" agora possui os valores: [5, 3, 18]
  ```

- Percorrendo o array: <br>
  Para percorrer todo o array, podemos usar o for ou o for-each

  ```java
  for (int i = 0; i < numeros.size(); i++) {
    System.out.print(numeros.get(i) + " "); // 5, 3, 18
  }
  ```

### Diferenças entre Array e ArrayList

| Array | ArrayList | Funcionalidade |
| ----- | --------- | -------------- |
| `int[] num = new int[5]` | `List<Integer> num = new ArrayList<>` | Criação |
| Tamanho fixo | Tamanho dinâmico | Quantidade de elementos |
| `arr[i] = x` | `arr.add(x)` | Atribuir um valor |
| `arr[i]` | `arr.get(i)` | Acessar um elemento |
| `arr[i] = y` | `arr.set(i, y)` | Modificar um elemento |
| `arr.length` | `arr.size()` | Obter o tamanho |
| Não possui métodos | Possui muitos (`contains`, `clear`, `indexOf`) | Manipulação de Dados |
| Não remove elementos | `arr.remove(i)` | Remoção |
| Aceita tipos primitivos | Usa objetos (wrapper) | Tipos Suportados |
| Mais rápido | Um pouco mais lento | Desempenho |
| Menor | Maior | Uso de Memória |