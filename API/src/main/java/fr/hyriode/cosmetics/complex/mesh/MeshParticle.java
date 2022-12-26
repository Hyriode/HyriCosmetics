package fr.hyriode.cosmetics.complex.mesh;

import fr.hyriode.cosmetics.complex.point.Point;
import net.minecraft.server.v1_8_R3.EnumParticle;

public class MeshParticle<T extends EnumParticle> extends Mesh<MeshParticle<?>> {

    private final T particle;
    private float speed = 0F;
    private float amount = 0F;

    public MeshParticle(T particle) {
        this.particle = particle;
    }

    public MeshParticle(T particle, float speed, float amount) {
        this.particle = particle;
        this.speed = speed;
        this.amount = amount;
    }

    public T particle() {
        return this.particle;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void translate(Point to, float duration) {

    }

    @Override
    public void moveToPoint(Point point) {

    }
}