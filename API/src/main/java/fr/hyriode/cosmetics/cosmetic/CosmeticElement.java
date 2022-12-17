package fr.hyriode.cosmetics.cosmetic;

import fr.hyriode.cosmetics.cosmetic.mesh.Mesh;
import fr.hyriode.cosmetics.cosmetic.point.Point;

import java.util.HashMap;

public interface CosmeticElement<T extends Mesh<?>> {

    void setMesh(T mesh, Point point);

    T getMesh();

    HashMap<Integer, Point> getPoints();

    void putPoint(int tick, Point point);

    int getAnimationTime();

    int getAnimationTick();

    void setAnimationTime(int animationTime);

    void tick();

    Cosmetic getCosmetic();
}