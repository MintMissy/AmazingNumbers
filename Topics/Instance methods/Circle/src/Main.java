class Circle {

    double radius;

    public double getLength() {
        return this.radius * 2 * Math.PI;
    }

    public double getArea() {
        return Math.pow(this.radius, 2) * Math.PI;
    }
}