package fr.hyriode.cosmetics.cosmetic.animation;

import fr.hyriode.cosmetics.cosmetic.CosmeticElement;
import fr.hyriode.cosmetics.cosmetic.point.Point;

public class AnimationPoint {

    private final int tick;
    private final CosmeticElement<?> element;
    private final Point point;

    public AnimationPoint(int tick, CosmeticElement<?> element, Point point) {
        this.tick = tick;
        this.element = element;
        this.point = point;
    }

    public int getTick() {
        return tick;
    }

    public CosmeticElement<?> getElement() {
        return element;
    }

    public Point getPoint() {
        return point;
    }
}
