package labs.lab6;

public class FigureUtils {
    public static void displayFigureInfo(Figure figure) {
        System.out.println("Class: " + figure.getClass().getSimpleName());
        figure.printInfo();
        System.out.println("-------------------------");
    }
}
