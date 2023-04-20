package fr.hyriode.cosmetics.balloon;

import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractBalloonImpl extends AbstractBalloon {

    public AbstractBalloonImpl(CosmeticUser user, Cosmetic cosmetic, boolean hasVariants) {
        super(user, cosmetic, hasVariants);
    }

    @Override
    protected void tick(CosmeticUser user) {

    }
}
