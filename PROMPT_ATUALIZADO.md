# +Prompt atualizado — Projeto Java de Estruturas de Dados

Preciso de desenvolver um programa em **Java**, utilizando **Maven**, para um trabalho académico de **Estruturas de Dados**.

O objetivo é criar um sistema que represente exclusivamente três formas geométricas:

1. Triângulos;

1. Retângulos;

1. Círculos.

## Arquitetura principal obrigatória

O modelo deve ser baseado em **três objetos principais**:

### 1. `Node`

Representa um nó, ponto ou vértice da estrutura.

Cada `Node` deve conter, no mínimo:

- um identificador;

- coordenadas `x` e `y`;

- referência para o nó anterior, `previous`;

- referência para o nó seguinte, `next`.

Quando não existir uma ligação anterior ou seguinte, a referência respetiva deve ser `null`.

### 2. `Connection`

Representa uma ligação entre dois objetos `Node`.

Deve conter, no mínimo:

- o nó de origem, `from`;

- o nó de destino, `to`.

Uma ligação `N1 -> N2` deve atualizar também os nós envolvidos:

```java
N1.next = N2;
N2.previous = N1;
```

### 3. `Shape`

Representa uma forma geométrica.

Não devem ser criadas subclasses de domínio separadas como `Triangle`, `Rectangle` e `Circle`. Deve existir uma única classe `Shape`, com um tipo definido por uma enumeração:

```java
TRIANGLE
RECTANGLE
CIRCLE
```

Cada `Shape` deve possuir:

- o seu `ShapeType`;

- uma lista ligada manual de objetos `Node`;

- uma lista ligada manual de objetos `Connection`;

- o raio, quando a forma for um círculo.

As classes `Node`, `Connection` e `Shape` são os três objetos principais do domínio. Podem existir outras classes auxiliares para implementar listas, estratégias, validação, criação e apresentação.

## Requisitos das formas

### Triângulo

- exatamente 3 objetos `Node`;

- exatamente 3 objetos `Connection`;

- as ligações devem fechar a estrutura:

```
T1 -> T2 -> T3 -> T1
```

- os três pontos não podem ser colineares.

### Retângulo

- exatamente 4 objetos `Node`;

- exatamente 4 objetos `Connection`;

- as ligações devem fechar a estrutura:

```
R1 -> R2 -> R3 -> R4 -> R1
```

- a largura e a altura devem ser positivas.

### Círculo

Um círculo não possui vértices matemáticos nem arestas retas.

Para representar o círculo de forma coerente com a estrutura do trabalho:

- utilizar um objeto `Node` de referência, identificado por `C`, no centro do círculo;

- guardar o raio no objeto `Shape`;

- não criar ligações retas para o círculo;

- `C.previous` deve ser `null`;

- `C.next` deve ser `null`;

- a lista de `Connection` do círculo deve ficar vazia.

Esta decisão deve ser explicada nos comentários do código e na documentação.

## Implementação manual das listas ligadas

A estrutura deve utilizar listas ligadas implementadas manualmente.

Não utilizar:

- `ArrayList`;

- `LinkedList`;

- outras coleções que escondam o funcionamento das listas ligadas;

- frameworks ou bibliotecas externas.

Podem ser criadas classes auxiliares como:

- `LinkedNodeList` — lista ligada de objetos `Node`;

- `LinkedConnectionList` — lista ligada de objetos `Connection`.

Cada entrada da lista deve possuir uma referência para a entrada seguinte, por exemplo:

```java
private Entry nextEntry;
```

A implementação deve demonstrar claramente como os elementos são inseridos, percorridos e obtidos.

## `current`, `previous` e `next`

Durante a navegação pela estrutura, o programa deve identificar:

- `current` — nó atual;

- `previous` — nó anterior ao atual;

- `next` — nó seguinte ao atual.

O percurso deve ser demonstrado com código semelhante a:

```java
Node current = shape.getStartNode();

while (current != null) {
    Node previous = current.getPrevious();
    Node next = current.getNext();

    // mostrar current, previous e next
    current = next;
}
```

Nos polígonos fechados, o percurso deve ter um limite igual ao número de nós, porque a última ligação regressa ao primeiro nó e cria um ciclo.

O código deve tratar corretamente referências `null` e evitar `NullPointerException`.

## Strategy Pattern obrigatório

Aplicar o **Strategy Pattern** para separar a forma como cada tipo de figura é construído.

Criar uma interface semelhante a:

```java
public interface ShapeCreationStrategy {
    ShapeType supportedType();
    int expectedParameterCount();
    Shape create(double... values);
}
```

Criar Strategies auxiliares:

- `TriangleCreationStrategy`;

- `RectangleCreationStrategy`;

- `CircleCreationStrategy`.

Cada Strategy deve construir uma única instância de `Shape` com o respetivo `ShapeType`, inserir os objetos `Node`, criar os objetos `Connection` e validar a estrutura.

Não criar subclasses `Triangle`, `Rectangle` ou `Circle`.

A classe `ShapeFactory` deve funcionar como Contexto do Strategy Pattern:

```
Main
  -> ShapeFactory.create("triangle", valores)
      -> TriangleCreationStrategy
          -> Shape
              -> Node e Connection
```

## Validação dos tipos permitidos

O programa deve impedir a criação de qualquer forma que não seja triângulo, retângulo ou círculo.

Utilizar uma enumeração fechada:

```java
public enum ShapeType {
    TRIANGLE,
    RECTANGLE,
    CIRCLE
}
```

A `ShapeFactory` deve rejeitar valores como:

- `PENTAGON`;

- `PENTAGONO`;

- `SQUARE`;

- `HEXAGON`;

- qualquer outro texto;

- `null`.

Deve ser lançada uma exceção clara, como `IllegalArgumentException`.

## Maven

O projeto deve utilizar a estrutura Maven convencional:

```
geometry-linked-list-java/
├── pom.xml
└── src/
    └── main/
        └── java/
```

Os ficheiros `.java` devem estar obrigatoriamente em:

```
src/main/java/
```

O `pom.xml` deve:

- definir o `groupId`;

- definir o `artifactId`;

- definir a versão do projeto;

- configurar Java 17 ou superior;

- permitir compilar com Maven;

- permitir executar a classe `Main` com `mvn exec:java`.

Comandos esperados:

```bash
mvn clean compile
mvn exec:java
```

## Visualização no terminal

O programa deve apresentar no terminal uma representação ASCII das três formas:

### Triângulo

```
        /\\
       /  \\
      /    \\
     /      \\
    /________\\
```

### Retângulo

```
    +------------+
    |            |
    |            |
    |            |
    +------------+
```

### Círculo

```
       *****
    **       **
  **           **
 *               *
 *               *
  **           **
    **       **
       *****
```

Criar uma classe auxiliar como `TerminalShapeRenderer` para esta representação. A renderização é apenas visual e não deve substituir as listas ligadas ou os objetos principais.

## `Main`

Criar uma classe `Main` que:

1. crie um triângulo através da `ShapeFactory`;

1. crie um retângulo através da `ShapeFactory`;

1. crie um círculo através da `ShapeFactory`;

1. mostre cada forma em ASCII no terminal;

1. mostre os objetos `Connection` existentes;

1. mostre, para cada nó, `previous`, `current` e `next`;

1. percorra as ligações usando `next`;

1. demonstre que o círculo tem `previous == null` e `next == null`;

1. demonstre a rejeição de uma forma inválida, como um pentágono.

## Organização sugerida das classes

```
src/main/java/
├── model/
│   ├── Node.java
│   ├── Connection.java
│   ├── Shape.java
│   └── ShapeType.java
├── repository/
│   ├── LinkedNodeList.java
│   └── LinkedConnectionList.java
├── strategy/
│   ├── ShapeCreationStrategy.java
│   ├── TriangleCreationStrategy.java
│   ├── RectangleCreationStrategy.java
│   └── CircleCreationStrategy.java
├── control/
│   ├── ShapeFactory.java
│   └── Main.java
└── view/
    ├── ShapeNavigator.java
    └── TerminalShapeRenderer.java
```

## Documentação obrigatória

Incluir um `README.md` que explique:

- os três objetos principais: `Node`, `Connection` e `Shape`;

- a diferença entre uma lista ligada e as ligações `previous` / `next` dos nós;

- como as `Connection` ligam os `Node`;

- como funciona `current`, `previous` e `next`;

- por que aparecem valores `null`;

- como o círculo é representado;

- como o Strategy Pattern foi aplicado;

- como a aplicação impede tipos de formas não permitidos;

- como compilar e executar com Maven.

## Resultado esperado

Entregar um projeto Java Maven completo, claro e adequado para um trabalho académico de Estruturas de Dados, em que:

- `Node`, `Connection` e `Shape` são os três objetos principais;

- as listas ligadas são implementadas manualmente;

- apenas existem três tipos de formas;

- o Strategy Pattern é utilizado para a criação;

- `previous`, `current` e `next` são demonstrados;

- o círculo é tratado de forma coerente;

- as formas aparecem visualmente no terminal;

- o projeto compila e executa com Maven.

