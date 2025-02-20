using System;

namespace VectorLibrary
{
    public class Vector
    {
        private double x;
        private double y;

        public Vector()
        {
            x = 0;
            y = 0;
        }

        public Vector(double x, double y)
        {
            this.x = x;
            this.y = y;
        }

        public Vector(Vector v)
        {
            this.x = v.x;
            this.y = v.y;
        }

        public double X => x;
        public double Y => y;

        public double Magnitude()
        {
            return Math.Sqrt(x * x + y * y);
        }

        public double Angle()
        {
            return Math.Atan2(y, x);
        }

        public double AngleDegrees()
        {
            return Angle() * (180 / Math.PI);
        }

        public override string ToString()
        {
            return $"Vector({x}, {y})";
        }
    }
}
