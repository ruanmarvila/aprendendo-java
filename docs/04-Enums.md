# Enums

## Índice

- [O que são Enums](#o-que-são-enums)
- [Enum Básico](#enum-básico)
  - [name() e ordinal()](#name-e-ordinal)
- [Enum com Switch](#enum-com-switch)
- [Enum com Atributos e Construtor](#enum-com-atributos-e-construtor)
- [Enum com Métodos](#enum-com-métodos)
  - [Valores Formatados com Map Interno](#valores-formatados-com-map-interno)
- [Enum Implementando Interface](#enum-implementando-interface)
- [Comparando com Python](#comparando-com-python)

---

## O que são Enums

`Enum` (enumeration) é um tipo especial que representa um conjunto **fixo e conhecido** de valores nomeados. É usado quando uma variável só pode assumir um número limitado de opções — como dias da semana, status de um pedido, ou naipes de baralho — em vez de aceitar qualquer `String` ou `int` livremente.

```java
public enum DiaSemana {
    SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO, DOMINGO;
}
```

Usar um `enum` em vez de `String`/`int` soltos evita erros comuns, como digitar `"segunda-feira"` num lugar e `"Segunda"` em outro — o compilador garante que só os valores declarados existem.

```java
// Sem enum: qualquer String "passa", mesmo com erro de digitação
String dia = "Segnda"; // typo, mas compila normalmente

// Com enum: o compilador não deixa passar valor inválido
DiaSemana dia = DiaSemana.SEGUNDA; // só aceita valores que existem no enum
```

## Enum Básico

Por baixo dos panos, cada valor de um `enum` é uma instância única daquele tipo — internamente, o Java trata `DiaSemana.SEGUNDA` quase como um objeto `static final`.

```java
DiaSemana dia = DiaSemana.SEGUNDA;

System.out.println(dia); // SEGUNDA
```

Um `enum` também pode ser usado dentro de estruturas condicionais e coleções, como qualquer outro tipo:

```java
if (dia == DiaSemana.SABADO || dia == DiaSemana.DOMINGO) {
    System.out.println("Fim de semana!");
}
```

### name() e ordinal()

Todo `enum` herda automaticamente alguns métodos, sem precisar declarar nada:

```java
DiaSemana dia = DiaSemana.QUARTA;

System.out.println(dia.name());    // "QUARTA" (o nome exato, como String)
System.out.println(dia.ordinal()); // 2 (posição na declaração, começando em 0)
```

| Método | Retorna | Exemplo (`QUARTA`) |
| ------ | ------- | ------------------- |
| `name()` | O nome do valor, como `String` | `"QUARTA"` |
| `ordinal()` | A posição do valor na declaração (começando em 0) | `2` |
| `toString()` | Por padrão, igual a `name()` (pode ser sobrescrito) | `"QUARTA"` |

> **Observação:** `ordinal()` é frágil — se você reordenar os valores no `enum` (ou inserir um novo no meio), a posição de todos os valores seguintes muda. Evite depender de `ordinal()` para lógica de negócio (como salvar em banco de dados); prefira `name()` ou um valor customizado.

## Enum com Switch

Um dos usos mais comuns de `enum` é dentro de um `switch`, já que o compilador sabe exatamente quais são os valores possíveis:

```java
DiaSemana dia = DiaSemana.SABADO;

switch (dia) {
    case SABADO:
    case DOMINGO:
        System.out.println("Hora de descansar");
        break;
    default:
        System.out.println("Dia de trabalho");
}
```

> Repare que, dentro do `switch`, não é necessário escrever `DiaSemana.SABADO` — só `SABADO`. O compilador já sabe o tipo pelo valor de `dia`.

Com Switch Expression (Java 14+), fica ainda mais direto:

```java
String status = switch (dia) {
    case SABADO, DOMINGO -> "Fim de semana";
    default -> "Dia de trabalho";
};

System.out.println(status);
```

## Enum com Atributos e Construtor

Diferente de outras linguagens, um `enum` em Java pode ter atributos, construtor e métodos — cada valor listado se comporta quase como uma mini-instância de classe.

```java
public enum Planeta {
    MERCURIO(3.7),
    TERRA(9.8),
    JUPITER(23.1);

    private final double gravidade;

    Planeta(double gravidade) { // construtor do enum: chamado uma vez por valor, na inicialização
        this.gravidade = gravidade;
    }

    public double getGravidade() {
        return gravidade;
    }
}
```

```java
System.out.println(Planeta.TERRA.getGravidade()); // 9.8
```

- O construtor de um `enum` é sempre `private` (implicitamente) — não é possível criar novas instâncias com `new Planeta(...)` fora da própria declaração. Os únicos objetos que existem são os listados no início.
- É possível ter mais de um atributo no construtor:

```java
public enum Status {
    EM_PREPARO("em_preparo", "Em Preparo"),
    ENVIADO("enviado", "Enviado"),
    ENTREGUE("entregue", "Entregue");

    private final String valor;
    private final String descricaoFormatada;

    Status(String valor, String descricaoFormatada) {
        this.valor = valor;
        this.descricaoFormatada = descricaoFormatada;
    }

    public String getValor() {
        return valor;
    }

    public String getDescricaoFormatada() {
        return descricaoFormatada;
    }
}
```

## Enum com Métodos

Além de getters simples, um `enum` pode ter métodos com lógica própria, e cada valor pode até sobrescrever um método individualmente:

```java
public enum Operacao {
    SOMAR {
        @Override
        public int aplicar(int a, int b) {
            return a + b;
        }
    },
    SUBTRAIR {
        @Override
        public int aplicar(int a, int b) {
            return a - b;
        }
    };

    public abstract int aplicar(int a, int b);
}
```

```java
System.out.println(Operacao.SOMAR.aplicar(5, 3));    // 8
System.out.println(Operacao.SUBTRAIR.aplicar(5, 3)); // 2
```

> Esse padrão (método abstrato dentro do `enum`, cada valor implementando o seu) é usado quando o comportamento muda bastante de valor pra valor. Para casos mais simples, um método comum (não abstrato) já resolve.

### Valores Formatados com Map Interno

Um padrão útil quando você precisa de uma versão "amigável" de cada valor (por exemplo, pra exibir no front-end) é montar um `Map` estático dentro do próprio `enum`:

```java
import java.util.Map;

public enum TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE;

    private static final Map<TaskStatus, String> FORMATADO_MAP = Map.of(
        TODO, "Todo",
        IN_PROGRESS, "In Progress",
        DONE, "Done"
    );

    public String getFormatado() {
        return FORMATADO_MAP.getOrDefault(this, this.name());
    }
}
```

```java
TaskStatus status = TaskStatus.IN_PROGRESS;
System.out.println(status.getFormatado()); // In Progress
```

- `static final`: o mapa é criado **uma única vez**, na primeira vez que o `enum` é carregado, e reaproveitado em todas as chamadas — não recalcula a cada `getFormatado()`.
- `this` dentro do método se refere ao próprio valor que está chamando — `TaskStatus.IN_PROGRESS.getFormatado()` executa com `this == IN_PROGRESS`.
- `getOrDefault()` evita erro caso algum valor não esteja mapeado, retornando `this.name()` como alternativa segura.

## Enum Implementando Interface

Um `enum` em Java **não pode herdar de outra classe** (já herda implicitamente de `Enum`, e Java tem herança simples), mas **pode implementar interfaces**, normalmente:

```java
interface Descritivel {
    String descricao();
}

public enum Status implements Descritivel {
    ATIVO,
    INATIVO;

    @Override
    public String descricao() {
        return switch (this) {
            case ATIVO -> "Conta ativa";
            case INATIVO -> "Conta inativa";
        };
    }
}
```

```java
Descritivel status = Status.ATIVO;
System.out.println(status.descricao()); // Conta ativa
```

Isso é útil quando você precisa que múltiplos `enum`s diferentes sigam o mesmo contrato, permitindo tratá-los de forma genérica através da interface — o mesmo princípio de abstração que você já viu em Interfaces, aplicado a `enum`s.

## Comparando com Python

| Python (`Enum`) | Java (`enum`) |
| --- | --- |
| `class Status(Enum): ATIVO = 1` | `enum Status { ATIVO }` |
| Herda de `Enum` (ou `str, Enum`, `IntEnum`, etc.) | Herda implicitamente de `Enum`, não pode herdar de outra classe |
| Métodos com `def` dentro da classe | Métodos declarados normalmente, ou até sobrescritos por valor |
| `@property` para valor derivado | Método `get...()` comum (Java não tem sintaxe de propriedade) |
| Comparação por identidade/valor mais flexível | Comparação com `==` funciona com segurança, já que cada valor é único |
| Pode ser combinado com `str`/`int` diretamente (`class Status(str, Enum)`) | Não existe equivalente direto — o "valor bruto" precisa ser um atributo próprio, definido no construtor |

> A diferença central: em Python, `Enum` é uma classe utilitária que você estende para ganhar comportamento de enumeração. Em Java, `enum` é uma **palavra-chave própria da linguagem** — o compilador trata de forma especial (garante unicidade dos valores, impede `new`, integra automaticamente com `switch`), tornando-o mais rígido, porém mais seguro em tempo de compilação.