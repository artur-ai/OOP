package labs.lab2_4;

public class Expression {
    private double a;
    private double c;
    private double d;

    public Expression(double a, double c, double d) {
        this.a = a;
        this.c = c;
        this.d = d;
    }

    public double getA() {
        return a;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public double computeLog(double value) throws IllegalArgumentException {
        if (value <= 0) {
            throw new IllegalArgumentException("Log can be only positive");
        }
        return Math.log(value);
    }

    public double computeExpression() throws ArithmeticException, IllegalArgumentException {
        double numerator = computeLog(2 * c - a) + d - 152;
        double denominator = a / 4.0 + c;

        if (denominator == 0) {
            throw new ArithmeticException("Denominator must be > 0");
        }

        return numerator / denominator;
    }

    @Override
    public String toString(){
        return String.format("Expression(a=%.2f, c=%.2f, d=%.2f)", a, c, d);
    }
}
