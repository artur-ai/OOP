#include <iostream>
#include "Vector.h"

int main() {
    double x, y;
    std::cout << "Введіть координати кінця вектора (x y): ";
    std::cin >> x >> y;

    Vector v(x, y);
    
    std::cout << "Довжина вектора: " << v.getMagnitude() << std::endl;
    std::cout << "Кут (у радіанах): " << v.getAngle() << std::endl;

    return 0;
}
