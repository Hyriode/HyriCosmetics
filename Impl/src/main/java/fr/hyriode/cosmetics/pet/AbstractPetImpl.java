package fr.hyriode.cosmetics.pet;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.CosmeticInfo;
import fr.hyriode.cosmetics.task.TaskNode;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractPetImpl<T> extends AbstractPet<T> {

    protected TaskNode task;

    public AbstractPetImpl(CosmeticUser user, CosmeticInfo info, boolean hasVariants) {
        super(user, info, hasVariants);
    }

    @Override
    public void onEquip(CosmeticUser user) {
        task = HyriCosmetics.get().getTaskProvider().initTaskNode(new TaskNode(() -> tick(user)));
    }

    @Override
    public void onUnequip(CosmeticUser user) {
        HyriCosmetics.get().getTaskProvider().removeTaskNode(task.getUUID());
    }

    protected abstract void spawn();

    protected abstract void remove();

    protected abstract void moveAnimationTick();

    public abstract void motionlessAnimationTick();

}
