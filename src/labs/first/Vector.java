package labs.first;

public class Vector {
    private double x;
    private double y;

    public Vector() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Vector (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector (Vector v) {
        this.x = v.x;
        this.y = v.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }

    public double getRadius(){
        return Math.sqrt(x * x + y * y);
    }

    public double getAngle() {
        return Math.atan2(y, x);
    }

    @Override
    public String toString() {
        return "Vector (" + x + ", " + y + ")";
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Vector" + this + " is being destroyed");
    }
}
