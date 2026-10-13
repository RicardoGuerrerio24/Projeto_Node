package strategy;

import model.Node;
import model.Shape;
import model.ShapeType;

/** Strategy concreta que constrói uma Shape do tipo RECTANGLE. */
public final class RectangleCreationStrategy implements ShapeCreationStrategy {
    @Override
    public ShapeType supportedType() {
        return ShapeType.RECTANGLE;
    }

    @Override
    public int expectedParameterCount() {
        return 4;
    }

    @Override
    public Shape create(double... values) {
        double x = values[0];
        double y = values[1];
        double width = values[2];
        double height = values[3];
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("A largura e a altura têm de ser positivas.");
        }

        Node n1 = new Node("R1", x, y);
        Node n2 = new Node("R2", x + width, y);
        Node n3 = new Node("R3", x + width, y + height);
        Node n4 = new Node("R4", x, y + height);
        Shape shape = new Shape(ShapeType.RECTANGLE);
        shape.addNode(n1);
        shape.addNode(n2);
        shape.addNode(n3);
        shape.addNode(n4);
        shape.connect(n1, n2);
        shape.connect(n2, n3);
        shape.connect(n3, n4);
        shape.connect(n4, n1);
        shape.validateStructure();
        return shape;
    }
}
