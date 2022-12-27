package fr.hyriode.cosmetics.complex;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.complex.animation.AnimationPoint;
import fr.hyriode.cosmetics.complex.animation.CosmeticAnimation;
import fr.hyriode.cosmetics.complex.point.Point;
import fr.hyriode.cosmetics.user.task.CosmeticTask;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public abstract class ComplexCosmetic {

    private CosmeticTask task;
    private final List<ComplexCosmeticElement<?>> childrens = new ArrayList<>();
    private final List<CosmeticAnimation> animations = new ArrayList<>();

    protected Point point;

    public void addChild(ComplexCosmeticElement<?> child) {
        childrens.add(child);
    }

    public List<ComplexCosmeticElement<?>> getChildrens() {
        return childrens;
    }

    public List<CosmeticAnimation> getAnimations() {
        return animations;
    }

    protected void addAnimation(CosmeticAnimation animation) {
        this.animations.add(animation);
    }

    protected void init() {
        for (CosmeticAnimation animation : animations) {
            for (AnimationPoint animationPoint : animation.getPoints()) {
                animationPoint.getElement().putPoint(animationPoint.getTick(), animationPoint.getPoint());
            }
        }
        this.task = HyriCosmetics.get().getTaskProvider().execute(this::tick);
    }

    public void delete() {
        HyriCosmetics.get().getTaskProvider().remove(task);
    }

    protected void tick() {
        for (ComplexCosmeticElement<?> child : childrens) {
            child.tick();
        }
    }

    public abstract Location getLocation();

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
