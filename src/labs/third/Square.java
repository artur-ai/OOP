package labs.third;

class Square {
        private int x1, y1, x2, y2;

        public Square() {
            this(0, 0, 1, 1);
        }

        public Square(int x1, int y1, int x2, int y2) {
            this.x1 = x1; this.y1 = y1;
            this.x2 = x2; this.y2 = y2;
        }

        public Square(Square other) {
            this(other.x1, other.y1, other.x2, other.y2);
        }

        public int getSide() {
            return Math.abs(x2 - x1);
        }

        public int area() { return getSide() * getSide(); }
        public double area(double multiplier) { return area() * multiplier; }

        public int perimeter() { return 4 * getSide(); }

        public void scale(int factor) { this.x2 = this.x1 + getSide() * factor; }

        public void update(Square other) {
            this.x1 = other.x1;
            this.y1 = other.y1;
            this.x2 = other.x2;
            this.y2 = other.y2;
        }

        public void move() { move(1, 1); }
        public void move(int dx, int dy) { x1 += dx; x2 += dx; y1 += dy; y2 += dy; }

        public static Square add(Square... squares) {
            int maxSide = 0;
            for (Square s : squares) maxSide = Math.max(maxSide, s.getSide());
            return new Square(0, 0, maxSide, maxSide);
        }

        public void print() {
            System.out.println("Квадрат (" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + "), Площа: " + area() + ", Периметр: " + perimeter());
        }

        public static void main(String[] args) {
            Square K1 = new Square();
            Square K2 = new Square(0, 0, 4, 4);
            Square K3 = new Square(K2);

            K3.move(5, 5);
            K3.print();

            K1 = Square.add(K2, K3);
            K1.print();
        }
    }


