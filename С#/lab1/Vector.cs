using System;

class Vector
{
    private double x, y; 

    public Vector(double x = 0, double y = 0)
    {
        this.x = x;
        this.y = y;
    }

    public double X
    {
        get { return x; }
        set { x = value; }
    }

    public double Y
    {
        get { return y; }
        set { y = value; }
    }

    public double GetMagnitude()
    {
        return Math.Sqrt(x * x + y * y);
    }

    public double GetAngle()
    {
        return Math.Atan2(y, x);
    }
}
