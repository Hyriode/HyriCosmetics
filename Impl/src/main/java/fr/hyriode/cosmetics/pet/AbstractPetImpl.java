package fr.hyriode.cosmetics.pet;

import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.task.TaskNode;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractPetImpl extends AbstractPet {

    protected TaskNode task;

    public AbstractPetImpl(CosmeticUser user, Cosmetic cosmetic) {
        super(user, cosmetic);
    }

    protected abstract void spawn();

    protected abstract void remove();

    protected abstract void moveAnimationTick();

    public abstract void motionlessAnimationTick();

}
