package fr.hyriode.cosmetics.complex;

import fr.hyriode.cosmetics.complex.mesh.Mesh;
import fr.hyriode.cosmetics.complex.point.Point;

import java.util.HashMap;

public interface ComplexCosmeticElement<T extends Mesh<?>> {

    void setMesh(T mesh, Point point);

    T getMesh();

    HashMap<Integer, Point> getPoints();

    void putPoint(int tick, Point point);

    int getAnimationTime();

    int getAnimationTick();

    void setAnimationTime(int animationTime);

    void tick();

    ComplexCosmetic getCosmetic();
}