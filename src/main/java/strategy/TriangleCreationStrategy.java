package strategy;

import model.Node;
import model.Shape;
import model.ShapeType;

/** Strategy concreta que constrói uma Shape do tipo TRIANGLE. */
public final class TriangleCreationStrategy implements ShapeCreationStrategy {
    @Override
    public ShapeType supportedType() {
        return ShapeType.TRIANGLE;
    }

    @Override
    public int expectedParameterCount() {
        return 6;
    }

    @Override
    public Shape create(double... values) {
        Node n1 = new Node("T1", values[0], values[1]);
        Node n2 = new Node("T2", values[2], values[3]);
        Node n3 = new Node("T3", values[4], values[5]);
        if (twiceArea(n1, n2, n3) == 0.0) {
            throw new IllegalArgumentException("Os vértices do triângulo não podem ser colineares.");
        }
        Shape shape = new Shape(ShapeType.TRIANGLE);
        shape.addNode(n1);
        shape.addNode(n2);
        shape.addNode(n3);
        shape.connect(n1, n2);
        shape.connect(n2, n3);
        shape.connect(n3, n1);
        shape.validateStructure();
        return shape;
    }

    private static double twiceArea(Node a, Node b, Node c) {
        return a.getX() * (b.getY() - c.getY())
                + b.getX() * (c.getY() - a.getY())
                + c.getX() * (a.getY() - b.getY());
    }
}
