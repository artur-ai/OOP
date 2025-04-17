package labs.lab6;

public class Main {
    public static void main(String[] args) {
        Triangle triangle = new Triangle(0, 0, 4, 0, 0, 3);
        Circle circle = new Circle(5.0);

        Figure fig1 = triangle;
        Figure fig2 = circle;

        FigureUtils.displayFigureInfo(fig1);
        FigureUtils.displayFigureInfo(fig2);
    }
}
