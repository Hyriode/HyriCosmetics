package fr.hyriode.cosmetics.particle.effect;

import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.particle.AbstractParticleImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.Location;
import xyz.xenondevs.particle.ParticleEffect;

import java.security.SecureRandom;
import java.util.Random;

public class EnchantedParticle extends AbstractParticleImpl {

    private static final Random random = new SecureRandom();

    public EnchantedParticle(CosmeticUser user) {
        super(user, Cosmetics.ENCHANTED);
    }

    @Override
    public void tick(final CosmeticUser user) {
        final Location location = user.asBukkit().getLocation();
        float dx = (float) location.getX() - 1.5f + random.nextFloat() % 3f - 1.5f;
        float dy = (float) location.getY() + random.nextFloat() % 2f;
        float dz = (float) location.getZ() - 1.5f  + random.nextFloat() % 3f - 1.5f;
        this.display(ParticleEffect.ENCHANTMENT_TABLE, dx, dy, dz);
    }
}
