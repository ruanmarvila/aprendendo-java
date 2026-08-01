# Exceções

## Índice

- [O que são Exceções](#o-que-são-exceções)
- [Try, Catch e Finally](#trycatch-e-finally)
  - [Try/Catch](#trycatch)
  - [Finally](#finally)
  - [Múltiplos Catch](#múltiplos-catch)
- [Throw x Throws](#throw-x-throws)
- [Checked x Unchecked](#checked-x-unchecked)
- [Exceções Customizadas](#exceções-customizadas)
  - [Herdando de Exception ou RuntimeException](#herdando-de-exception-ou-runtimeexception)
  - [Sobrecarga em Exceções](#sobrecarga-em-exceções)
  - [Um Arquivo por Exceção](#um-arquivo-por-exceção)
- [Hierarquia de Exceções Customizadas](#hierarquia-de-exceções-customizadas)

## O que são Exceções

Uma exceção é um evento que interrompe o fluxo normal do programa quando algo inesperado acontece durante a execução — uma divisão por zero, um arquivo que não existe, um índice de array inválido.

Em Java, uma exceção é representada por um **objeto**, instância de uma classe que herda (direta ou indiretamente) de `Throwable`. Quando um erro acontece, dizemos que a exceção foi **lançada** (`throw`), e o programa procura um bloco capaz de **tratá-la** (`catch`) subindo pela pilha de chamadas até encontrar um, ou encerrando o programa se não encontrar nenhum.

```java
int[] numeros = {1, 2, 3};
System.out.println(numeros[5]); // lança ArrayIndexOutOfBoundsException
```

Sem tratamento, esse erro interrompe o programa e imprime o **stack trace** — o caminho de chamadas que levou até o erro.


## Try/Catch e Finally

Tratamento de exceções

### Try/Catch

O bloco `try` contém o código que pode falhar. O `catch` intercepta a exceção, caso ela aconteça, e decide o que fazer.

```java
try {
    int resultado = 10 / 0;
    System.out.println(resultado); // nunca chega aqui
} catch (ArithmeticException e) {
    System.out.println("Não é possível dividir por zero: " + e.getMessage());
}
```

- Se nenhuma exceção acontecer dentro do `try`, o `catch` é simplesmente ignorado.
- Se uma exceção acontecer e não houver um `catch` compatível com o tipo dela, o programa é interrompido mesmo assim.

### Finally

O bloco `finally` executa **sempre**, tenha o `try` lançado exceção ou não. É usado para liberar recursos (fechar arquivos, conexões, `Scanner`), garantindo que isso aconteça independente do resultado.

```java
Scanner scanner = new Scanner(System.in);

try {
    System.out.print("Digite um número: ");
    int numero = scanner.nextInt();
    System.out.println(100 / numero);
} catch (ArithmeticException e) {
    System.out.println("Não é possível dividir por zero");
} finally {
    scanner.close(); // executa sempre, com ou sem erro
    System.out.println("Scanner fechado");
}
```

> Mesmo que o `catch` tenha um `return`, o `finally` ainda assim é executado antes do método realmente retornar.

### Múltiplos Catch

Um `try` pode ter vários `catch`, cada um tratando um tipo diferente de exceção. Java verifica os blocos **na ordem em que aparecem**, então o mais específico deve vir antes do mais genérico.

```java
try {
    int[] numeros = new int[3];
    numeros[5] = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Erro aritmético: " + e.getMessage());
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Índice inválido: " + e.getMessage());
} catch (Exception e) {
    System.out.println("Erro genérico: " + e.getMessage()); // sempre por último
}
```

> **Observação:** se `catch (Exception e)` viesse antes dos outros, o compilador acusaria erro — como `Exception` é a superclasse de todas as outras, ela captura tudo, tornando os `catch` seguintes inalcançáveis (*unreachable code*).

Também é possível tratar múltiplos tipos no mesmo bloco, usando `|`, quando o tratamento for igual para os dois:

```java
try {
    // código que pode lançar tanto IOException quanto SQLException
} catch (IOException | SQLException e) {
    System.out.println("Erro: " + e.getMessage());
}
```

## Throw x Throws

Apesar do nome parecido, `throw` e `throws` têm papéis diferentes.

| | `throw` | `throws` |
| --- | --- | --- |
| O que faz | Lança uma exceção, na hora | Declara que um método *pode* lançar uma exceção |
| Onde fica | Dentro do corpo do método | Na assinatura do método |
| Quantidade | Uma exceção por vez | Pode listar várias, separadas por vírgula |

```java
// throw: lança a exceção efetivamente
public void sacar(double valor, double saldo) {
    if (valor > saldo) {
        throw new IllegalArgumentException("Saldo insuficiente");
    }
}
```

```java
// throws: avisa que o método pode lançar, sem lançar diretamente aqui
public void lerArquivo(String caminho) throws IOException {
    FileReader leitor = new FileReader(caminho); // esse método já lança IOException internamente
}

// throws com múltiplas exceções
public void processar() throws IOException, SQLException {
    // ...
}
```

`throws` é obrigatório quando o método lança (ou chama algo que lança) uma **checked exception** e não a trata internamente com `try/catch` — é a forma de "repassar" a responsabilidade de tratar para quem chamar o método.


## Checked x Unchecked

Java divide as exceções em dois grupos, e essa divisão não existe no Python — é uma das diferenças mais importantes.

### Checked Exceptions

São verificadas em **tempo de compilação**. Se um método lança (ou pode lançar) uma checked exception, o compilador **obriga** você a tratá-la com `try/catch` ou declará-la com `throws`. Representam situações externas e previsíveis, como falhas de I/O.

```java
public void lerArquivo() throws IOException { // obrigatório, senão não compila
    FileReader leitor = new FileReader("dados.txt");
}
```

Exemplos comuns: `IOException`, `SQLException`, `ClassNotFoundException`.

### Unchecked Exceptions

São subclasses de `RuntimeException`. O compilador **não exige** tratamento — elas só aparecem em tempo de execução, geralmente indicando um erro de lógica do programador.

```java
public void dividir(int a, int b) {
    System.out.println(a / b); // ArithmeticException, sem exigir try/catch nem throws
}
```

Exemplos comuns: `NullPointerException`, `ArithmeticException`, `ArrayIndexOutOfBoundsException`, `IllegalArgumentException`.

### Hierarquia

```
Throwable
├── Error (erros graves da JVM, não devem ser tratados: OutOfMemoryError)
└── Exception
    ├── RuntimeException (unchecked)
    │   ├── NullPointerException
    │   ├── ArithmeticException
    │   └── ArrayIndexOutOfBoundsException
    └── IOException, SQLException, ... (checked)
```

| | Checked | Unchecked |
| --- | --- | --- |
| Verificada em | Compilação | Execução |
| Herda de | `Exception` (direto) | `RuntimeException` |
| Compilador exige tratamento? | Sim | Não |
| Representa | Falhas externas previsíveis | Erros de lógica/programação |

> Em Python, todo erro se comporta essencialmente como uma unchecked exception — não existe verificação em tempo de compilação. É por isso que esse conceito costuma ser o mais estranho.

## Exceções Customizadas

Você pode criar suas próprias exceções para representar erros específicos do seu domínio, herdando de `Exception` (checked) ou `RuntimeException` (unchecked).

### Herdando de Exception ou RuntimeException

```java
// Unchecked: mais comum para erros de regra de negócio
public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
```

```java
// Checked: obriga quem chamar a tratar
public class ContaInvalidaException extends Exception {
    public ContaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
```

> Na prática, a maioria dos projetos prefere `RuntimeException` para exceções de negócio (evita `throws` espalhado por toda a aplicação), reservando checked exceptions para situações realmente externas e recuperáveis.

### Sobrecarga em Exceções

Como Java não tem parâmetros opcionais, usamos sobrecarga de construtores para simular uma mensagem padrão:

```java
public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException() {
        super("Saldo insuficiente para realizar a operação");
    }

    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
```

```java
throw new SaldoInsuficienteException();                                          // mensagem padrão
throw new SaldoInsuficienteException("Saldo insuficiente para saque de R$ 500"); // customizada
```

### Um Arquivo por Exceção

Como toda exceção customizada é uma classe `public`, ela segue a regra geral do Java: **uma classe pública por arquivo**, com o nome do arquivo igual ao da classe.

- SaldoInsuficienteException.java
- ContaInvalidaException.java

Em projetos Spring organizados por domínio, é comum agrupar essas exceções numa subpasta dentro do próprio domínio:

```
user/
├── User.java
├── UserService.java
└── exceptions/
    ├── UserNotFoundException.java
    └── DuplicateEmailException.java
```

## Hierarquia de Exceções Customizadas

Quando um projeto tem várias exceções customizadas, é comum criar uma **exceção base** da qual todas as outras herdam. Isso permite tratar todas de forma centralizada, aproveitando o polimorfismo.

```java
public class ModelException extends RuntimeException {
    private final HttpStatus status;

    public ModelException(String mensagem, HttpStatus status) {
        super(mensagem);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
```

```java
public class UserNotFoundException extends ModelException {
    public UserNotFoundException(String mensagem) {
        super(mensagem, HttpStatus.NOT_FOUND);
    }
}

public class ProductOutOfStockException extends ModelException {
    public ProductOutOfStockException(String mensagem) {
        super(mensagem, HttpStatus.BAD_REQUEST);
    }
}
```

Cada exceção nova define seu próprio status ao chamar `super(...)`, mas todas compartilham o mesmo comportamento base — inclusive o mesmo `getStatus()`, herdado de `ModelException`.

> **Nota:** esse é o mesmo padrão usado em frameworks web (como Spring, com `@RestControllerAdvice`, ou FastAPI, com `@app.exception_handler`) para capturar qualquer exceção do projeto com um único handler central, lendo a mensagem e o status diretamente do objeto lançado — em vez de escrever um tratamento repetido para cada tipo de erro.