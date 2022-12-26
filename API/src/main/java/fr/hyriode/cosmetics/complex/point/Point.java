package fr.hyriode.cosmetics.complex.point;

import org.bukkit.Location;
import org.bukkit.World;

public class Point {

    private float offsetX;
    private float offsetY;
    private float offsetZ;

    public Point(float offsetX, float offsetY, float offsetZ) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public float getOffsetZ() {
        return offsetZ;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public void setOffsetZ(float offsetZ) {
        this.offsetZ = offsetZ;
    }

    public Point clone() {
        return new Point(this.offsetX, this.offsetY, this.offsetZ);
    }

    public Point add(float offsetX, float offsetY, float offsetZ) {
        this.offsetX += offsetX;
        this.offsetY += offsetY;
        this.offsetZ += offsetZ;

        return this;
    }

    public Point subtract(float offsetX, float offsetY, float offsetZ) {
        this.offsetX -= offsetX;
        this.offsetY -= offsetY;
        this.offsetZ -= offsetZ;

        return this;
    }

    public Location toLocation(World world) {
        return new Location(world, this.offsetX, this.offsetY, this.offsetZ);
    }

    @Override
    public String toString() {
        return "Point{" +
                "offsetX=" + offsetX +
                ", offsetY=" + offsetY +
                ", offsetZ=" + offsetZ +
                '}';
    }
}
