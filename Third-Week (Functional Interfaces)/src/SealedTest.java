sealed class Shape permits Circle, Rectangle {
    String color;

    Shape(String color) {
        this.color = color;
    }

    String getColor() {
        return color;
    }

    double area() {
        return 0.0;
    }
}

final class Circle extends Shape {
    double radius;

    Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    double area() {
        return Math.PI * radius * radius;
    }
}

non-sealed class Rectangle extends Shape {
    double width, height;

    Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    double area() {
        return width * height;
    }
}

class Square extends Rectangle {
    Square(String color, double side) {
        super(color, side, side);
    }
}



public class SealedTest {
    public static void main(String[] args) {
        Shape c = new Circle("Red", 3.0);
        Shape r = new Rectangle("Blue", 4.0, 5.0);
        Shape s = new Square("Green", 6.0);

        System.out.println(c.getColor() + " Circle area: " + ((Circle)c).area());
        System.out.println(r.getColor() + " Rectangle area: " + ((Rectangle)r).area());
        System.out.println(s.getColor() + " Square area: " + ((Square)s).area());
    }
}
