package strategy;

import model.Shape;
import model.ShapeType;

/**
 * Strategy do Strategy Pattern.
 * Cada implementação sabe como criar exactamente um tipo permitido de forma.
 */
public interface ShapeCreationStrategy {
    ShapeType supportedType();

    int expectedParameterCount();

    Shape create(double... values);
}
