package control;

import model.Shape;
import model.ShapeType;
import strategy.CircleCreationStrategy;
import strategy.RectangleCreationStrategy;
import strategy.ShapeCreationStrategy;
import strategy.TriangleCreationStrategy;

import java.util.Locale;

/**
 * Contexto do Strategy Pattern.
 * A fábrica delega a criação para a estratégia concreta correspondente.
 */
public final class ShapeFactory {
    private ShapeFactory() {
        // Classe utilitária.
    }

    public static Shape create(String requestedType, double... values) {
        ShapeType type = parseType(requestedType);
        ShapeCreationStrategy strategy = strategyFor(type);
        validateParameterCount(type, strategy, values);
        return strategy.create(values);
    }

    private static ShapeCreationStrategy strategyFor(ShapeType type) {
        return switch (type) {
            case TRIANGLE -> new TriangleCreationStrategy();
            case RECTANGLE -> new RectangleCreationStrategy();
            case CIRCLE -> new CircleCreationStrategy();
        };
    }

    private static ShapeType parseType(String requestedType) {
        if (requestedType == null) {
            throw new IllegalArgumentException("O tipo de forma não pode ser null.");
        }
        try {
            return ShapeType.valueOf(requestedType.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Forma não permitida: " + requestedType
                            + ". Apenas TRIANGLE, RECTANGLE e CIRCLE são aceites.",
                    exception);
        }
    }

    private static void validateParameterCount(
            ShapeType type, ShapeCreationStrategy strategy, double[] values) {
        int received = values == null ? 0 : values.length;
        if (received != strategy.expectedParameterCount()) {
            throw new IllegalArgumentException(
                    type + " requer " + strategy.expectedParameterCount()
                            + " valores; foram recebidos " + received + ".");
        }
    }
}
