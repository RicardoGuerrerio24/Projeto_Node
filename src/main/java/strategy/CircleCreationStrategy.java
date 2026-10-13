package strategy;

import model.Node;
import model.Shape;
import model.ShapeType;

/** Strategy concreta que constrói uma Shape do tipo CIRCLE. */
public final class CircleCreationStrategy implements ShapeCreationStrategy {
    @Override
    public ShapeType supportedType() {
        return ShapeType.CIRCLE;
    }

    @Override
    public int expectedParameterCount() {
        return 3;
    }

    @Override
    public Shape create(double... values) {
        Shape shape = new Shape(ShapeType.CIRCLE);
        shape.addNode(new Node("C", values[0], values[1]));
        shape.setRadius(values[2]);
        shape.validateStructure();
        return shape;
    }
}
