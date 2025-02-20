using System;

class Program
{
    static void Main()
    {
        Console.Write("Введіть координати кінця вектора (x y): ");
        string[] input = Console.ReadLine().Split();
        double x = double.Parse(input[0]);
        double y = double.Parse(input[1]);

        Vector v = new Vector(x, y);

        Console.WriteLine("Довжина вектора: " + v.GetMagnitude());
        Console.WriteLine("Кут (у радіанах): " + v.GetAngle());
    }
}
