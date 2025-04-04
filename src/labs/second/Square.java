package labs.second;

class Square {
    private int x, y;
    private int side;

    public Square() {
        this.x = 0;
        this.y = 0;
        this.side = 1;
    }

    public Square(int x, int y, int side) {
        this.x = x;
        this.y = y;
        this.side = side;
    }

    public Square(Square other) {
        this.x = other.x;
        this.y = other.y;
        this.side = other.side;
    }

    public int getArea() {
        return side * side;
    }

    public int getPerimeter() {
        return 4 * side;
    }

    public void printInfo() {
        System.out.println("Square: (" + x + ", " + y + ") Side: " + side + " Area: " + getArea() + " Perimeter: " + getPerimeter());
    }

    public Square add(int value) {
        return new Square(this.x, this.y, this.side + value);
    }

    public Square divide(Square other) {
        if (other.side == 0) {
            throw new ArithmeticException("Cannot divide by zero-size square");
        }
        return new Square(this.x, this.y, this.side / other.side);
    }

    public static void main(String[] args) {
        Square K1 = new Square();
        Square K2 = new Square(2, 2, 10);
        Square K3 = new Square(K2);

        K3 = K3.add(5);
        K1 = K2.divide(K3);

        K1.printInfo();
        K2.printInfo();
        K3.printInfo();
    }
}
