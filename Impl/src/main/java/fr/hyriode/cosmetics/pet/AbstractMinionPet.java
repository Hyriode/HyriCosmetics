package fr.hyriode.cosmetics.pet;

import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractMinionPet extends AbstractPetImpl {

    public AbstractMinionPet(CosmeticUser user, Cosmetics cosmetic) {
        super(user, cosmetic);
    }

    @Override
    protected void tick(CosmeticUser user) {
        if (isMoving()) {
            this.moveAnimationTick();
        } else {
            this.motionlessAnimationTick();
        }
    }
}
