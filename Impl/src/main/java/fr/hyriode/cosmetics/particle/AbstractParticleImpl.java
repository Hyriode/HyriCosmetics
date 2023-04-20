package fr.hyriode.cosmetics.particle;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.task.TaskNode;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractParticleImpl<T> extends AbstractParticle<T> {

    protected TaskNode task;

    public AbstractParticleImpl(CosmeticUser user, Cosmetic cosmetic, boolean hasVariants) {
        super(user, cosmetic, hasVariants);
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
}
