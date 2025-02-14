#ifndef VECTOR_H
#define VECTOR_H

class Vector {
private:
    double x, y;

public:
    Vector(double x = 0, double y = 0);

    double getX() const;
    double getY() const;
    
    void setX(double x);
    void setY(double y);

    double getMagnitude() const;
    double getAngle() const;
};

#endif
