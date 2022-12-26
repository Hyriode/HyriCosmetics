package fr.hyriode.cosmetics.complex;

import fr.hyriode.cosmetics.complex.point.Point;

public enum Attachment {

    HEAD(0F, 0F, 0F);

    private final Point point;

    Attachment(float offsetX, float offsetY, float offsetZ) {
        this.point = new Point(offsetX, offsetY, offsetZ);
    }

    public Point getPoint() {
        return point;
    }

}