#include "Vector.h"
#include <cmath>

Vector::Vector(double x, double y) : x(x), y(y) {}

double Vector::getX() const { return x; }
double Vector::getY() const { return y; }

void Vector::setX(double x) { this->x = x; }
void Vector::setY(double y) { this->y = y; }

double Vector::getMagnitude() const {
    return std::sqrt(x * x + y * y);
}

double Vector::getAngle() const {
    return std::atan2(y, x);
}
