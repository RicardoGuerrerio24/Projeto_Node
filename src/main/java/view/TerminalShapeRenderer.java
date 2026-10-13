package view;

import model.Shape;

/**
 * Renderiza as três formas permitidas usando apenas caracteres do terminal.
 *
 * Esta classe não altera a estrutura geométrica: é apenas uma visualização
 * simples para tornar o percurso e a forma visíveis durante a execução.
 */
public final class TerminalShapeRenderer {
    private TerminalShapeRenderer() {
        // Classe utilitária.
    }

    public static void print(Shape shape) {
        if (shape == null) {
            throw new IllegalArgumentException("Não é possível desenhar uma forma null.");
        }

        System.out.println("Representação visual no terminal:");
        switch (shape.getType()) {
            case TRIANGLE -> printTriangle();
            case RECTANGLE -> printRectangle();
            case CIRCLE -> printCircle();
        }
    }

    private static void printTriangle() {
        System.out.println("        /\\");
        System.out.println("       /  \\");
        System.out.println("      /    \\");
        System.out.println("     /      \\");
        System.out.println("    /________\\");
    }

    private static void printRectangle() {
        System.out.println("    +------------+");
        System.out.println("    |            |");
        System.out.println("    |            |");
        System.out.println("    |            |");
        System.out.println("    +------------+");
    }

    private static void printCircle() {
        System.out.println("       *****");
        System.out.println("    **       **");
        System.out.println("  **           **");
        System.out.println(" *               *");
        System.out.println(" *               *");
        System.out.println("  **           **");
        System.out.println("    **       **");
        System.out.println("       *****");
    }
}
