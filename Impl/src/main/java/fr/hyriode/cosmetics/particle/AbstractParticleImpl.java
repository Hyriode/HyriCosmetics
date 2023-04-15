package fr.hyriode.cosmetics.particle;

import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.task.TaskNode;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.hyrame.IHyrame;
import org.bukkit.Location;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public abstract class AbstractParticleImpl extends AbstractParticle {

    public AbstractParticleImpl(CosmeticUser user, Cosmetics cosmetic) {
        super(user, cosmetic);
    }

    @Override
    TaskNode initTask(final CosmeticUser user) {
        return new TaskNode(() -> this.tick(user));
    }

    @Override
    public void onEquip(final CosmeticUser user) {
        super.onEquip(user);
    }

    @Override
    public void onUnequip(final CosmeticUser user) {
        super.onUnequip(user);
    }

    public abstract void tick(final CosmeticUser user);

    protected void display(ParticleEffect effect, float x, float y, float z) {
        new ParticleBuilder(ParticleEffect.valueOf(effect.name()), new Location(IHyrame.WORLD.get(), x, y, z))
                .setAmount(1)
                .display();
    }

}
