# Figuras geométricas com Maven, listas ligadas e três objetos principais

Projeto académico em Java baseado em três objetos principais do domínio:

1. `Node` — representa um nó/vértice;
2. `Connection` — representa uma ligação entre dois nós;
3. `Shape` — representa a forma geométrica.

As restantes classes são auxiliares de implementação, organizadas por responsabilidade nos packages `repository`, `strategy`, `control` e `view`.

## Estrutura Maven

```text
geometry-linked-list-java/
├── pom.xml
└── src/main/java/
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

## Execução

Requisitos: Java 17+ e Maven.

```bash
mvn clean compile
mvn exec:java
```

## Organização por packages

- `model`: objetos principais do domínio (`Node`, `Connection`, `Shape`) e `ShapeType`;
- `repository`: listas ligadas manuais que armazenam `Node` e `Connection`;
- `strategy`: interface e estratégias concretas de criação;
- `control`: fábrica e ponto de entrada `Main`;
- `view`: navegação, apresentação dos nós e desenho ASCII no terminal.

## Arquitetura dos três objetos principais

### `Node`

É o nó/vértice da estrutura. Guarda o identificador, as coordenadas e duas referências:

```java
private Node previous;
private Node next;
```

`previous` aponta para o nó anterior e `next` aponta para o seguinte. Quando não existe ligação, a referência é `null`.

### `Connection`

É a ligação entre dois objetos `Node`:

```java
private final Node from;
private final Node to;
```

Uma ligação `N1 -> N2` significa que `N1.next` passa a apontar para `N2` e `N2.previous` passa a apontar para `N1`.

### `Shape`

É o terceiro objeto principal. Não existem classes de domínio separadas `Triangle`, `Rectangle` ou `Circle`. Uma única `Shape` contém um `ShapeType`:

```java
TRIANGLE | RECTANGLE | CIRCLE
```

Além do tipo, `Shape` mantém manualmente:

- uma `LinkedNodeList` de objetos `Node`;
- uma `LinkedConnectionList` de objetos `Connection`;
- o raio, quando o tipo é `CIRCLE`.

A classe `Shape` valida as quantidades exigidas para cada tipo.

## Regras geométricas

| Tipo | Nós | Ligações | Representação |
|---|---:|---:|---|
| `TRIANGLE` | 3 | 3 | `T1 -> T2 -> T3 -> T1` |
| `RECTANGLE` | 4 | 4 | `R1 -> R2 -> R3 -> R4 -> R1` |
| `CIRCLE` | 1 | 0 | Nó `C` de referência no centro e raio |

O círculo não possui vértices matemáticos nem arestas rectas. Por isso, usa um único nó de referência no centro, com `previous == null` e `next == null`, e guarda o raio em `Shape`.

## Listas ligadas manuais

`LinkedNodeList` e `LinkedConnectionList` são estruturas auxiliares. Cada uma possui uma classe interna de entrada com uma referência `nextEntry`.

Estas classes não substituem os três objetos principais; apenas guardam os objetos `Node` e `Connection` sem usar `ArrayList` ou `LinkedList`.

## Strategy Pattern

O Strategy Pattern foi mantido como mecanismo auxiliar de criação:

- `ShapeCreationStrategy` — interface;
- `TriangleCreationStrategy` — constrói uma `Shape` do tipo `TRIANGLE`;
- `RectangleCreationStrategy` — constrói uma `Shape` do tipo `RECTANGLE`;
- `CircleCreationStrategy` — constrói uma `Shape` do tipo `CIRCLE`;
- `ShapeFactory` — selecciona a estratégia apropriada.

As estratégias não criam subclasses de `Shape`. Todas criam o mesmo objeto principal `Shape` e preenchem a sua lista de `Node` e de `Connection`.

Exemplo:

```text
Main
  -> ShapeFactory.create("triangle", valores)
      -> TriangleCreationStrategy
          -> new Shape(ShapeType.TRIANGLE)
          -> new Node(...)
          -> new Connection(...)
```

## Percurso com `current`, `previous` e `next`

`ShapeNavigator` inicia em `shape.getStartNode()` e usa:

```java
Node current = shape.getStartNode();
Node previous = current.getPrevious();
Node next = current.getNext();
current = next;
```

Nos polígonos, a última ligação regressa ao primeiro nó. O percurso é limitado ao número de nós para evitar um ciclo infinito. No círculo, o nó central tem `previous` e `next` nulos, pelo que o percurso termina imediatamente.

## Impedir outros tipos

- `ShapeType` contém apenas `TRIANGLE`, `RECTANGLE` e `CIRCLE`;
- `ShapeFactory` rejeita qualquer texto que não corresponda a esses três valores;
- `Shape` só aceita um valor válido de `ShapeType` no construtor;
- não existem classes ou estratégias para pentágonos, quadrados ou outras formas.

## Visualização no terminal

`TerminalShapeRenderer` apresenta uma representação ASCII de cada `Shape`. A visualização não altera a estrutura de dados; serve apenas para tornar as três formas visíveis ao executar `mvn exec:java`.
