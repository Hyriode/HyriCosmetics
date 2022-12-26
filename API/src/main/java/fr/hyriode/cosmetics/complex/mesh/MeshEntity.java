package fr.hyriode.cosmetics.complex.mesh;

import fr.hyriode.cosmetics.complex.ComplexCosmeticElement;
import fr.hyriode.cosmetics.complex.point.Point;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class MeshEntity<T extends Entity> extends Mesh<MeshEntity<?>> {

    private final T entity;
    private final ComplexCosmeticElement<MeshEntity<T>> element;

    public MeshEntity(T entity, ComplexCosmeticElement<MeshEntity<T>> element) {
        this.entity = entity;
        this.element = element;
    }

    public T getEntity() {
        return entity;
    }

    public void translate(int tick, float offsetX, float offsetY, float offsetZ) {
        entity.teleport(new Location(
                entity.getWorld(),
                entity.getLocation().getX() + offsetX * tick,
                entity.getLocation().getY() + offsetY * tick,
                entity.getLocation().getZ() + offsetZ * tick)
        );
    }

    public ComplexCosmeticElement<MeshEntity<T>> getElement() {
        return element;
    }

    @Override
    public void setPoint(Point point) {
        super.setPoint(point);
    }

    @Override
    public void moveToPoint(Point point) {
        super.setPoint(point);
        final Location origin = element.getCosmetic().getLocation();
        final Location objective = element.getCosmetic().getLocation().clone().add(point.toLocation(entity.getWorld()));

        final double dX = origin.getX() - objective.getX();
        final double dY = origin.getY() - objective.getY();
        final double dZ = origin.getZ() - objective.getZ();

        final double yaw = Math.atan2(dZ, dX);
        final double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;

        final double X = Math.sin(pitch) * Math.cos(yaw);
        final double Y = Math.sin(pitch) * Math.sin(yaw);
        final double Z = Math.cos(pitch);

        final Vector vector = new Vector(X, Z, Y);

        entity.setVelocity(vector.multiply(0.1));
    }
}