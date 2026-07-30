# Programação Orientada a Objetos

## Índice

- [O que é POO](#o-que-é-poo)
- [Classe](#classe)
- [Objeto](#objeto)
- [Construtor](#construtor)
  - [This](#referência-this)
  - [Métodos de Object](#métodos-de-object)
- [Pilares do POO](#os-quatro-pilares-do-poo)
  - [Encapsulamento](#encapsulamento)
    - [Modificadores de Acesso](#modificadores-de-acesso)
    - [Getters e Setters](#getters-e-setters)
  - [Herança](#herança)
    - [Super](#super-e-construtor)
    - [Composição](#composição-tem-um)
  - [Polimorfismo](#polimorfismo)
    - [Sobrecarga x Sobrescrita](#sobrecarga-overload-x-sobrescrita-override)
  - [Abstração](#abstração)
    - [Classes Abstratas](#classes-abstratas)
    - [Interfaces](#interfaces)
    - [instanceof e Casting](#instanceof-e-casting-entre-tipos)

---
## O que é POO

A Programação Orientada a Objetos (POO) é um paradigma de programação que organiza o código utilizando objetos, que são representações de entidades do mundo real ou conceitos do sistema.

A POO junta os dados (atributos) e os comportamentos (métodos) dentro de uma estrutura chamada classe

Exemplo: 

Um sistema bancário pode ter uma entidade chamada `ContaBancaria`.

Uma conta possui dados:
- número
- titular
- saldo

E também comportamentos:
- depositar
- sacar
- ver extrato

## Classe

Uma classe é um molde utilizado para criar objetos

Ela define quais atributos e métodos os objetos daquele tipo terão.

Exemplo:

```java
public class Carro {

  // Atributos
  String modelo;
  String cor;
  int ano;

  // Método
  void ligar() {
    System.out.println("O carro " + modelo + " está ligando");
  }
}
```

  > **Observação:** A classe `Carro` não representa um carro específico, ela apenas define como um carro deve ser


## Objeto

Um objeto é uma instância de uma classe, ou seja, um elemento criado a partir daquele molde.

Se a classe é a planta de uma casa, o objeto é a casa construída

Para criar um objeto, usamos a palavra-chave `new`

- Sintaxe: `Classe nome = new Classe();`

Podemos criar vários objetos da mesma classe

Exemplo:

```java
Carro carro1 = new Carro();
Carro carro2 = new Carro();

carro1.modelo = "Civic";
carro1.cor = "Vermelho";
carro1.ano = 2020;

carro2.modelo = "Camaro";
carro2.cor = "Amarelo";
carro2.ano = 2012;

carro1.ligar(); // O carro Civic está ligando
carro2.ligar(); // O carro Camaro está ligando
```

## Construtor

O construtor é um método especial que é chamado quando o objeto é criado com `new`. Ele serve para inicializar o objeto, normalmente recebendo atributos.

- Sintaxe: `modificador NomeDaClasse(atributos) {...}`

```java
public class Cachorro {
  String nome;
  int idade;

  // Construtor
  public Cachorro(String nome, int idade) {
    this.nome = nome;
    this.idade = idade;
  }
}
```
```java
Cachorro mel = new Cachorro("Mel", 3);
System.out.println(mel.nome); // Mel
```

### Referência `this`

Dentro do construtor, os nomes dos parâmetros podem ser iguais aos dos atributos da classe. A palavra `this` se refere ao próprio objeto, desfazendo a ambiguidade quando os nomes são os mesmos.


## Métodos de Object

Toda classe em Java herda automaticamente alguns métodos da classe `Object`, mesmo sem escrever `extends Object`.

### toString()

Por padrão, imprimir um objeto mostra algo pouco útil, como o nome da classe e um código de hash.

```java
public class Carro {
  String modelo;

  public Carro(String modelo) {
    this.modelo = modelo;
  }
}
```
```java
Carro carro = new Carro("Civic");
System.out.println(carro); // Carro@1b6d3586
```

Sobrescrevendo `toString()`, controlamos essa representação.

```java
public class Carro {
  String modelo;

  public Carro(String modelo) {
    this.modelo = modelo;
  }

  @Override
  public String toString() {
    return "Carro: " + modelo;
  }
}
```
```java
Carro carro = new Carro("Civic");
System.out.println(carro); // Carro: Civic
```

### equals()

Por padrão, `equals()` compara referências (o mesmo que `==`). Sobrescrevendo, definimos o que significa dois objetos serem "iguais" em conteúdo.

```java
public class Carro {
  String modelo;

  public Carro(String modelo) {
    this.modelo = modelo;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof Carro)) return false;
    Carro outro = (Carro) obj;
    return modelo.equals(outro.modelo);
  }
}
```
```java
Carro c1 = new Carro("Civic");
Carro c2 = new Carro("Civic");

System.out.println(c1 == c2);      // false (referências diferentes)
System.out.println(c1.equals(c2)); // true (mesmo conteúdo)
```

### hashCode()

Sempre que sobrescrevemos `equals()`, devemos sobrescrever `hashCode()` também. Esse método gera um código numérico usado por coleções como `HashSet` e `HashMap` para organizar e localizar objetos rapidamente.

```java
@Override
public int hashCode() {
  return modelo.hashCode();
}
```

> **Regra:** se `a.equals(b)` é `true`, então `a.hashCode()` e `b.hashCode()` devem ser iguais. Quebrar essa regra causa bugs difíceis de encontrar em `HashSet` e `HashMap`.


## Os quatro pilares do POO

A POO tem quatro pilares: Encapsulamento, Herança, Polimorfismo e Abstração.

### Encapsulamento

Encapsulamento é a forma de proteger os atributos e a integridade do objeto, impedindo que qualquer parte do código possa alterar os detalhes internos dele, usando os modificadores `public`, `protected` e `private`, e oferecendo métodos para a leitura e alteração dos atributos mas de forma controlada.

#### Modificadores de Acesso

| Modificador | Mesma Classe | Mesmo Pacote | Subclasse (outro pacote) | Qualquer Lugar |
| ----------- | :-----------: | :-----------: | :-----------------------: | :-------------: |
| `private` | ✅ | ❌ | ❌ | ❌ |
| *(default)* | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

- **`private`:** só a própria classe acessa. É o mais usado para atributos, garantindo o encapsulamento.
- **`default`:** quando nenhum modificador é escrito, o acesso fica restrito ao pacote. É o único que não tem uma palavra-chave própria.
- **`protected`:** acessível no pacote e também por subclasses, mesmo que estejam em outro pacote.
- **`public`:** acessível de qualquer lugar.

> **Observação:** diferente do Python, onde a convenção de "privado" é só uma dica (`__atributo`), em Java o `private` é reforçado pelo compilador — tentar acessar um atributo privado de fora da classe gera erro de compilação.

Exemplo: 

```java
public class ContaBancaria {
  private double saldo;

  public double getSaldo() {
    return saldo;
  }

  public void depositar(double valor) {
    if (valor > 0) {
      saldo += valor; // Só aceita valores positivos
    }
  }
}
```

#### Getters e Setters

Getters são métodos usados para ler um atributo, enquanto setters são métodos usados para alterar um atributo. A vantagem de passar por eles, em vez de expor o atributo direto, é o poder de aplicar regras, como fazer `saldo` só aceitar valores positivos.


### Herança

Herança permite que uma classe (subclasse) reaproveite atributos e métodos de outra classe (superclasse) e acrescentar novos métodos. Em Java, usa-se a palavra `extends`.

```java
public class Animal {
  String nome;

  public Animal(String nome) {
    this.nome = nome;
  }

  void dormir() {
    System.out.println(nome + " está dormindo");
  }
}
```
```java
public class Gato extends Animal {

  public Gato(String nome) {
    super(nome); // Chama o construtor da superclasse
  }

  void miar() {
    System.out.println("miau miau!");
  }
}
```
```java
Gato felix = new Gato("Felix");
felix.dormir(); // Herdado de Animal
felix.miar();   // Próprio de Gato
```

> A herança é uma relação de "**é um**": `Gato` é um `Animal`

#### Super e Construtor

A subclasse pode chamar o construtor da superclasse através de `super(...)`. Java tem herança simples, uma classe estende de no máximo uma outra, o que evita ambiguidades. Toda classe que não estende explicitamente de ninguém, estende de `Object`, a raiz de toda classe.

#### Composição ("tem um")

Em vez de uma classe herdar de outra, ela pode simplesmente ter um atributo do tipo da outra classe. É a relação de "**tem um**", diferente do "é um" da herança.

```java
public class Motor {
  void ligar() {
    System.out.println("Motor ligado");
  }
}

public class Carro {
  private Motor motor; // Carro TEM UM Motor

  public Carro() {
    this.motor = new Motor();
  }

  void ligar() {
    motor.ligar(); // delega a ação para o Motor
    System.out.println("Carro pronto para andar");
  }
}
```
```java
Carro carro = new Carro();
carro.ligar();
/*
Motor ligado
Carro pronto para andar
*/
```

> Um `Carro` não é um tipo de `Motor` — não faria sentido `Carro extends Motor`. Mas um carro **possui** um motor, então composição é a escolha certa aqui.

**Quando usar cada um:**
- Use **herança** quando existe uma relação clara de "é um" (`Gato` é um `Animal`).
- Use **composição** quando existe uma relação de "tem um" (`Carro` tem um `Motor`), ou quando você só quer reaproveitar comportamento sem criar uma hierarquia rígida.

> Em Java moderno, é comum ouvir a recomendação **"favoreça composição em vez de herança"** — herança cria um acoplamento forte entre classes, enquanto composição é mais flexível para mudanças futuras.


### Polimorfismo

Polimorfismo permite que uma mesma chamada de método tenha comportamentos diferentes dependendo do tipo real do objeto. Isso acontece porque subclasses podem sobrescrever métodos da superclasse.

Uma subclasse pode sobrescrever (override) um método herdado da superclasse, dando um novo comportamento a ele. Usa-se `@Override` para indicar ao compilador que você está sobrescrevendo um método existente

```java
public class Animal {
  void emitirSom() {
    System.out.println("Som genérico");
  }
}
```
```java
public class Cachorro extends Animal {
  @Override
  void emitirSom() {
    System.out.println("Au au");
  }
}
```
```java
Animal a = new Cachorro();
a.emitirSom(); // Au au, e não Som genérico
```

Mesmo a referência sendo do tipo `Animal`, o obejeto é um `Cachorro`, então a versão sobrescrita é executada. Isso permite escrever código que funcione com qualquer `Animal` sem saber o tipo exato.

#### Sobrecarga (Overload) x Sobrescrita (Override)

Os dois termos parecem parecidos, mas acontecem em momentos diferentes.

| | Sobrecarga (Overload) | Sobrescrita (Override) |
| --- | --- | --- |
| Quando é decidido | Compilação (compile-time) | Execução (runtime) |
| Onde acontece | Mesma classe | Entre superclasse e subclasse |
| Assinatura | Mesmo nome, parâmetros diferentes | Mesmo nome e mesmos parâmetros |
| Palavra-chave | Nenhuma | `@Override` |

```java
public class Calculadora {
  // Sobrecarga: mesma classe, parâmetros diferentes
  public int somar(int a, int b) {
    return a + b;
  }

  public double somar(double a, double b) {
    return a + b;
  }
}
```

```java
public class Animal {
  void emitirSom() {
    System.out.println("Som genérico");
  }
}

public class Cachorro extends Animal {
  // Sobrescrita: mesma assinatura, comportamento novo
  @Override
  void emitirSom() {
    System.out.println("Au au");
  }
}
```

> Sobrecarga é decidida olhando o **tipo dos argumentos na chamada**. Sobrescrita é decidida olhando o **tipo real do objeto em tempo de execução**.


### Abstração

#### Classes Abstratas

Classes abstratas servem de base para outras, porém não podem ser instanciadas com `new`. Ela pode ter métodos comuns e métodos abstratos, que são declarados sem corpo e que as subclasses são obrigadas a implementar. Use `abstract` na classe e nos métodos.

```java
abstract class Forma {
  abstract double area();
}
```
```java
public class Quadrado extends Forma {
  private double lado;

  public Quadrado(double lado) {
    this.lado = lado;
  }

  @Override
  double area() {
    return lado * lado;
  }
}
```

#### Interfaces

Uma interface define um contrato, métodos que as classes precisam implementar de alguma forma. Usa-se `implements`. Diferente da herança, uma classe pode implementar várias interfaces.

```java
interface Voador {
  void voar();
}

interface Oviparo {
  void colocarOvo();
}
```
```java
public class Passaro implements Voador, Oviparo {
  public void voar() {
    System.out.println("Voando");
  }

  public void colocarOvo() {
    System.out.println("Pondo ovo");
  }
}
```

| Aspecto | Classe Abstrata | Interface |
| ------- | --------------- | --------- |
| Implementação | `extends` | `implements` |
| Quantidade | uma por classe | várias por classe |

#### instanceof e Casting entre Tipos

Quando trabalhamos com uma referência de um tipo mais genérico (uma superclasse ou interface), às vezes precisamos verificar o tipo real do objeto por trás dela.

```java
Animal animal = new Gato("Felix");

if (animal instanceof Gato) {
  System.out.println("É um gato!");
}
```

**Upcasting:** converter uma subclasse para o tipo da superclasse. Acontece de forma implícita, sem risco.

```java
Gato gato = new Gato("Felix");
Animal animal = gato; // upcasting implícito
```

**Downcasting:** converter uma superclasse de volta para uma subclasse específica. Precisa ser explícito, e pode gerar erro em tempo de execução (`ClassCastException`) se o objeto não for realmente daquele tipo.

```java
Animal animal = new Gato("Felix");

Gato gato = (Gato) animal; // downcasting explícito
gato.miar();
```

```java
Animal animal = new Cachorro("Rex");

Gato gato = (Gato) animal; // ClassCastException! Cachorro não é Gato
```

- Antes de fazer downcasting, é uma boa prática verificar o tipo com `instanceof` para evitar o `ClassCastException`.

```java
  if (animal instanceof Gato) {
    Gato gato = (Gato) animal;
    gato.miar();
  }
```

> **Observação:** esse é exatamente o padrão que o **Pattern Matching no switch** (Java 21+), já documentado lá em Condicionais, veio simplificar — ele une a verificação `instanceof` e o cast numa única linha, sem precisar declarar a variável separadamente.