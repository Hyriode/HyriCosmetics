package fr.hyriode.cosmetics.particle.effect;

import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.particle.AbstractParticleImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.utils.MathUtil;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import java.awt.Color;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.color.RegularColor;

public abstract class InvocationParticle extends AbstractParticleImpl {

    private static final double angle = 0.14D;
    private static final double angularVelocityY = 0.3D;
    private static final int max = 60;

    private int tick = 0;
    private double radius = 0.0D;
    private int height = 70;

    private final ParticleBuilder particleBuilder;

    public InvocationParticle(CosmeticUser user, ParticleBuilder particleBuilder) {
        super(user, Cosmetic.FIRE_INVOCATION, false);
        this.particleBuilder = particleBuilder;
    }

    @Override
    public void tick(final CosmeticUser user) {
        if (isMoving()) {
            particleBuilder.setLocation(this.getLocation()).setSpeed(0.1F).display();
            return;
        }

        this.tick++;
        this.tick %= 1200;
        Location location = getPlayer().getLocation().clone().subtract(0.0D, 0.3D, 0.0D);
        double d = tick * angularVelocityY;
        for (byte b = 1; b < 4; b++) {
            double d1 = (120 * b);
            Vector vector = new Vector(Math.cos(tick * angle + Math.toRadians(d1)) * this.radius, this.height * 0.05D, Math.sin(tick * angle + Math.toRadians(d1)) * this.radius);
            MathUtil.rotateAroundAxisY(vector, d);
            particleBuilder.setLocation(location.add(vector)).setSpeed(0.1F).display();
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

    public static class FireInvocationParticle extends InvocationParticle {
        public FireInvocationParticle(CosmeticUser user) {
            super(user, new ParticleBuilder(ParticleEffect.FLAME));
        }
    }

    public static class GemInvocationParticle extends InvocationParticle {
        public GemInvocationParticle(CosmeticUser user) {
            super(user, new ParticleBuilder(ParticleEffect.VILLAGER_HAPPY));
        }
    }

    public static class PortalInvocationParticle extends InvocationParticle {
        public PortalInvocationParticle(CosmeticUser user) {
            super(user, new ParticleBuilder(ParticleEffect.REDSTONE).setParticleData(new RegularColor(Co)));
        }
    }
}