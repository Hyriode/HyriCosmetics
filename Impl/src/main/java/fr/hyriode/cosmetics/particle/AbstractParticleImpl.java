package fr.hyriode.cosmetics.particle;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.CosmeticInfo;
import fr.hyriode.cosmetics.task.TaskNode;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractParticleImpl<T> extends AbstractParticle<T> {

    protected TaskNode task;

    public AbstractParticleImpl(CosmeticUser user, CosmeticInfo info, boolean hasVariants) {
        super(user, info, hasVariants);
    }

    @Override
    public void onEquip(final CosmeticUser user) {
        this.task = HyriCosmetics.get().getTaskProvider().initTaskNode(new TaskNode(() -> this.tick(user)));
    }

    @Override
    public void onUnequip(final CosmeticUser user) {
        HyriCosmetics.get().getTaskProvider().removeTaskNode(this.task.getUUID());
    }

    public abstract void tick(final CosmeticUser user);
}
