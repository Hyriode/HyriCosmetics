package fr.hyriode.cosmetics.cosmetic.mesh;

import fr.hyriode.cosmetics.cosmetic.point.Point;

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
