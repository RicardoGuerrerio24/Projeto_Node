package control;

import model.Shape;
import view.ShapeNavigator;

/**
 * Ponto de entrada. As formas são criadas através do Contexto ShapeFactory,
 * que delega para uma Strategy concreta de cada tipo.
 */
public final class Main {
    public static void main(String[] args) {
        Shape triangle = ShapeFactory.create("triangle", 0, 0, 6, 0, 3, 4);
        Shape rectangle = ShapeFactory.create("rectangle", 0, 0, 8, 3);
        Shape circle = ShapeFactory.create("circle", 5, 5, 2.5);

        ShapeNavigator.printShape(triangle);
        ShapeNavigator.printShape(rectangle);
        ShapeNavigator.printShape(circle);

        demonstrateFactoryValidation();
    }

    private static void demonstrateFactoryValidation() {
        System.out.println("\n=== VALIDAÇÃO DE TIPOS E STRATEGY PATTERN ===");

        Shape createdByFactory = ShapeFactory.create("triangle", 0, 0, 4, 0, 2, 3);
        System.out.println("Strategy usada para criar: " + createdByFactory.getType());

        try {
            ShapeFactory.create("pentagono", 0, 0, 1, 1, 2, 2);
        } catch (IllegalArgumentException exception) {
            System.out.println("Tentativa rejeitada: " + exception.getMessage());
        }
    }
}
