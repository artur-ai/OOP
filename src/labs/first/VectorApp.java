package labs.first;

import java.util.Scanner;


public class VectorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Vector v1 = new Vector();
        Vector v2 = new Vector(3, 4);
        Vector v3 = new Vector(v2);

        System.out.println("Default vector: " + v1);
        System.out.println("Vector with coordinates (3,4): " + v2);
        System.out.println("Copied vector: " + v3);

        System.out.print("Enter x coordinate: ");
        double x = scanner.nextDouble();
        System.out.print("Enter y coordinate: ");
        double y = scanner.nextDouble();

        Vector userVector = new Vector(x, y);
        System.out.println("Your vector: " + userVector);
        System.out.println("Polar coordinates: Radius = " + userVector.getRadius() + ", Angle = " + userVector.getAngle());

        scanner.close();
    }
}
