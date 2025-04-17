package labs.lab6;

public class Triangle extends Figure {
    private final double x1, y1, x2, y2, x3, y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public double getArea() {
        return Math.abs(
                (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
    }

    @Override
    public double getPerimeter() {
        return distance(x1, y1, x2, y2)
                + distance(x2, y2, x3, y3)
                + distance(x3, y3, x1, y1);
    }

    public String getCoordinates() {
        return String.format("A(%.1f, %.1f), B(%.1f, %.1f), C(%.1f, %.1f)", x1, y1, x2, y2, x3, y3);
    }
}
