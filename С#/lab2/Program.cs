using System;
using VectorLibrary;

class Program
{
    static void Main()
    {
        Console.WriteLine("=== Демонстрація класу Vector ===");

        Vector v1 = new Vector();
        Console.WriteLine($"v1: {v1}, Довжина: {v1.Magnitude()}, Кут: {v1.AngleDegrees()}°");

        Vector v2 = new Vector(3, 4);
        Console.WriteLine($"v2: {v2}, Довжина: {v2.Magnitude()}, Кут: {v2.AngleDegrees()}°");

        Vector v3 = new Vector(v2);
        Console.WriteLine($"v3 (копія v2): {v3}, Довжина: {v3.Magnitude()}, Кут: {v3.AngleDegrees()}°");

        Console.WriteLine("=== Програма завершена ===");
    }
}
