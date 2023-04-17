package fr.hyriode.cosmetics.particle.effect;

import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.particle.AbstractParticleImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.texture.BlockTexture;

public class FireInvocationParticle extends AbstractParticleImpl {

    private static final double angle = 0.14D;
    private static final double angularVelocityY = 0.3D;
    private static final int max = 60;

    private int tick = 0;
    private double radius = 0.0D;
    private int height = 70;

    public FireInvocationParticle(CosmeticUser user) {
        super(user, Cosmetics.FIRE_INVOCATION);
    }

    @Override
    public void tick(final CosmeticUser user) {
        if (isMoving()) {
            new ParticleBuilder(ParticleEffect.FLAME, getLocation()).setSpeed(0.1F).display();
            return;
        }

        this.tick++;
        this.tick %= 1200;
        Location location = getPlayer().getLocation().clone().subtract(0.0D, 0.3D, 0.0D);
        double d = tick * angularVelocityY;
        for (byte b = 1; b < 4; b++) {
            double d1 = (120 * b);
            Vector vector = new Vector(Math.cos(tick * angle + Math.toRadians(d1)) * this.radius, this.height * 0.05D, Math.sin(tick * angle + Math.toRadians(d1)) * this.radius);
            rotateAroundAxisY(vector, d);
            new ParticleBuilder(ParticleEffect.FLAME, location.add(vector)).setSpeed(0.1F).display();
            new ParticleBuilder(ParticleEffect.BLOCK_CRACK, location).setParticleData(new BlockTexture(Material.LAVA)).setOffset(0.1F, 0.1F, 0.1F).setSpeed(0F).display();
            location.subtract(vector);
        }
        if (this.height > max / 2) {
            this.radius += 0.035D;
        } else {
            this.radius -= 0.035D;
        }
        this.height--;
        if (this.height <= 0) {
            this.height = max;
            this.radius = 0.0D;
        }
    }

    private Vector rotateAroundAxisY(Vector paramVector, double paramDouble) {
        double d3 = Math.cos(paramDouble);
        double d4 = Math.sin(paramDouble);
        double d1 = paramVector.getX() * d3 + paramVector.getZ() * d4;
        double d2 = paramVector.getX() * -d4 + paramVector.getZ() * d3;
        return paramVector.setX(d1).setZ(d2);
    }
}