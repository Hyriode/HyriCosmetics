package fr.hyriode.cosmetics.complex;

import fr.hyriode.cosmetics.complex.mesh.Mesh;
import fr.hyriode.cosmetics.complex.point.Point;
import fr.hyriode.hyrame.utils.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class ComplexCosmeticElementImpl<T extends Mesh<?>> implements ComplexCosmeticElement<T> {

    private T mesh;
    private final ComplexCosmetic complexCosmetic;
    private final HashMap<Integer, Point> points = new HashMap<>();
    private int animationTime;
    private int animationTick;
    private Pair<Integer, Point> lastStep;
    private int currentStep = 0;

    public ComplexCosmeticElementImpl(final ComplexCosmetic complexCosmetic) {
        this.complexCosmetic = complexCosmetic;
    }

    @Override
    public void setMesh(final T mesh, final Point point) {
        this.mesh = mesh;
        this.mesh.setPoint(point);
        this.lastStep = new Pair<>(0, point);
    }

    @Override
    public T getMesh() {
        return mesh;
    }

    @Override
    public HashMap<Integer, Point> getPoints() {
        return points;
    }

    @Override
    public void putPoint(final int tick, final Point point) {
        System.out.println("Put point " + point + " at tick " + tick);
        if (tick > this.animationTime) animationTime += tick;
        this.points.put(tick, point);
    }

    @Override
    public int getAnimationTime() {
        return animationTime;
    }

    @Override
    public int getAnimationTick() {
        return animationTick;
    }

    @Override
    public void setAnimationTime(final int animationTime) {
        this.animationTime = animationTime;
    }

    @Override
    public void tick() {
        this.animationTick++;
        if (animationTick > animationTime) {
            animationTick = 0;
        }

        if (this.points.containsKey(animationTick)) {
            this.currentStep++;
            this.lastStep = new Pair<>(animationTick, this.points.get(animationTick));
        }

        Pair<Integer, Point> goal = getNextStep();
        int time = goal.getKey() - lastStep.getKey();
        final Point point = this.mesh.getPoint();
        this.mesh.moveToPoint(new Point(
                point.getOffsetX() + ((goal.getValue().getOffsetX() - lastStep.getValue().getOffsetX()) / time),
                point.getOffsetY() + ((goal.getValue().getOffsetY() - lastStep.getValue().getOffsetY()) / time),
                point.getOffsetZ() + ((goal.getValue().getOffsetZ() - lastStep.getValue().getOffsetZ()) / time)
        ));

        System.out.println("Tick: " + animationTick + " | Time: " + animationTime + " | Beginning: " + lastStep.getValue().toString() + " | Goal: " + goal.getValue().toString());
        //System.out.println(point.getOffsetX() + ((goal.getValue().getOffsetX() - lastStep.getValue().getOffsetX()) / time));
    }

    private Pair<Integer, Point> getNextStep() {
        if (currentStep >= this.points.size()) {
            currentStep = 0;
        }
        return new Pair<>(new ArrayList<>(this.points.keySet()).get(currentStep), new ArrayList<>(this.points.values()).get(currentStep));
    }

    @Override
    public ComplexCosmetic getCosmetic() {
        return complexCosmetic;
    }
}