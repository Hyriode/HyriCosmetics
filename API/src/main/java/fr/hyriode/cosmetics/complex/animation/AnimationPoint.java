package fr.hyriode.cosmetics.complex.animation;

import fr.hyriode.cosmetics.complex.ComplexCosmeticElement;
import fr.hyriode.cosmetics.complex.point.Point;

public class AnimationPoint {

    private final int tick;
    private final ComplexCosmeticElement<?> element;
    private final Point point;

    public AnimationPoint(int tick, ComplexCosmeticElement<?> element, Point point) {
        this.tick = tick;
        this.element = element;
        this.point = point;
    }

    public int getTick() {
        return tick;
    }

    public ComplexCosmeticElement<?> getElement() {
        return element;
    }

    public Point getPoint() {
        return point;
    }
}
