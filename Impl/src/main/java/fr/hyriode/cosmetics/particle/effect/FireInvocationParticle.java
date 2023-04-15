package fr.hyriode.cosmetics.particle.effect;

import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.particle.AbstractParticleImpl;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public class FireInvocationParticle extends AbstractParticleImpl {

    private final Player player;

    private int ticks = 0;

    public FireInvocationParticle(CosmeticUser user) {
        super(user, Cosmetics.FIRE_INVOCATION);

        this.player = getPlayer();
    }

    @Override
    public void tick(final CosmeticUser user) {
        if (isMoving()) {
            new ParticleBuilder(ParticleEffect.FLAME, getLocation()).setSpeed(0.1F).display();
        } else {
            tornado(ticks);
        }
        ticks++;
    }

    private void tornado(int angle) {
        final Location location = player.getLocation().clone().subtract(0, 2, 0);

    }
}