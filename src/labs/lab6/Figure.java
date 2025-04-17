package labs.lab6;

public abstract class Figure {
    public abstract double getArea();

    public abstract double getPerimeter();

    public void printInfo() {
        System.out.println("Area: " + getArea());
        System.out.println("Perimeter: " + getPerimeter());
    }
}
