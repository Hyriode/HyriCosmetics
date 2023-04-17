package fr.hyriode.cosmetics.particle.effect;

import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.particle.AbstractParticleImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.security.SecureRandom;
import java.util.Random;

public class EnchantedParticle extends AbstractParticleImpl {

    private static final Random random = new SecureRandom();
    private static final double radius = 1.0D;

    int points = 25;
    int i = 0;

    public EnchantedParticle(CosmeticUser user) {
        super(user, Cosmetics.ENCHANTED);
    }

    @Override
    public void tick(final CosmeticUser user) {
        if (isMoving()) {
            new ParticleBuilder(ParticleEffect.ENCHANTMENT_TABLE, getPlayer().getLocation().clone().add(getRandomVector()).add(0, 1, 0)).display();
        } else {
            this.i++;
            if (this.i == 5) {
                this.i = 0;
                for (byte b = 0; b < this.points; b++) {
                    double d = 6.283185307179586D * b / this.points;
                    Location location = getPlayer().getLocation().clone().add(radius * Math.sin(d), 2.0D, radius * Math.cos(d));
                    Vector vector = getPlayer().getLocation().add(0, 1, 0).toVector().subtract(getPlayer().getLocation().toVector()).normalize();
                    new ParticleBuilder(ParticleEffect.ENCHANTMENT_TABLE, location.clone().add(0, -1, 0).add(vector)).display();
                }
            }
        }
    }

    private Vector getRandomVector() {
        double d1 = random.nextDouble() * 2.0D - 1.0D;
        double d2 = random.nextDouble() * 2.0D - 1.0D;
        double d3 = random.nextDouble() * 2.0D - 1.0D;
        return (new Vector(d1, d2, d3)).normalize();
    }
}
