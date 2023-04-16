package fr.hyriode.cosmetics.particle;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.task.TaskNode;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.hyrame.IHyrame;
import org.bukkit.Location;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public abstract class AbstractParticleImpl extends AbstractParticle {

    protected TaskNode task;

    public AbstractParticleImpl(CosmeticUser user, Cosmetics cosmetic) {
        super(user, cosmetic);
    }

    @Override
    public void onEquip(final CosmeticUser user) {
        task = HyriCosmetics.get().getTaskProvider().initTaskNode(new TaskNode(() -> tick(user)));
    }

    @Override
    public void onUnequip(final CosmeticUser user) {
        HyriCosmetics.get().getTaskProvider().removeTaskNode(task.getUUID());
    }

    public abstract void tick(final CosmeticUser user);

    protected void display(ParticleEffect effect, float x, float y, float z) {
        new ParticleBuilder(ParticleEffect.valueOf(effect.name()), new Location(IHyrame.WORLD.get(), x, y, z))
                .setAmount(1)
                .display();
    }

}
