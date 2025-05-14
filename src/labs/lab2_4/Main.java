package labs.lab2_4;

public class Main {
    public static void main(String[] args) {
        Expression[] expressions = new Expression[]{
                new Expression(4, 3, 200),
                new Expression(1, 3, 160),
                new Expression(2, 1, 152),
                new Expression(4, -1, 100),
                new Expression(-4, 1, 100)
        };

        for (Expression expr : expressions) {
            try {
                double result = expr.computeExpression();
                System.out.printf("%s => Result: %.4f%n", expr, result);
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.printf("%s => Error: %s%n", expr, e.getMessage());
            }
        }
    }

}

