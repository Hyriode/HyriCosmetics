package fr.hyriode.cosmetics.cosmetic;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.cosmetic.animation.AnimationPoint;
import fr.hyriode.cosmetics.cosmetic.animation.CosmeticAnimation;
import fr.hyriode.cosmetics.cosmetic.point.Point;
import fr.hyriode.cosmetics.task.CosmeticTask;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public abstract class Cosmetic {

    private CosmeticTask task;
    private final List<CosmeticElement<?>> childrens = new ArrayList<>();
    private final List<CosmeticAnimation> animations = new ArrayList<>();

    protected Point point;

    public void addChild(CosmeticElement<?> child) {
        childrens.add(child);
    }

    public List<CosmeticElement<?>> getChildrens() {
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
        for (CosmeticElement<?> child : childrens) {
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
