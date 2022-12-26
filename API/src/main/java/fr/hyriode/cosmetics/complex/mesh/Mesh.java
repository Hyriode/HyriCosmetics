package fr.hyriode.cosmetics.complex.mesh;

import fr.hyriode.cosmetics.complex.point.Point;

public abstract class Mesh<T> {

    private T entity;

    private Point point;

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public abstract void moveToPoint(Point point);
}
