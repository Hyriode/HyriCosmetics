package fr.hyriode.cosmetics.particle.effect;

import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.particle.AbstractParticleImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.Location;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EnchantedParticle extends AbstractParticleImpl {

    private static final Random random = ThreadLocalRandom.current();
    private static final double radius = 1.0D;
    private static final int points = 25;

    private int tick = 0;


    public EnchantedParticle(CosmeticUser user) {
        super(user, Cosmetic.ENCHANTED, false);
        this.updateVariant();
    }

    @Override
    public void tick(final CosmeticUser user) {
        if (isMoving()) {
            new ParticleBuilder(ParticleEffect.ENCHANTMENT_TABLE, getPlayer().getLocation().clone().add(getRandomVector()).add(0, 1, 0)).display();
            return;
        }

        this.tick++;
        if (this.tick == 5) {
            this.tick = 0;
            for (byte b = 0; b < points; b++) {
                double d = Math.PI * 2 * b / points;
                Location location = getPlayer().getLocation().clone().add(radius * Math.sin(d), 2.0D, radius * Math.cos(d));
                Vector vector = getPlayer().getLocation().add(0, 1, 0).toVector().subtract(getPlayer().getLocation().toVector()).normalize();
                new ParticleBuilder(ParticleEffect.ENCHANTMENT_TABLE, location.clone().add(0, -1, 0).add(vector)).display();
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
