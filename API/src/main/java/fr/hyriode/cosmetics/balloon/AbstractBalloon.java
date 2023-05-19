package fr.hyriode.cosmetics.balloon;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticInfo;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractBalloon extends AbstractCosmetic {

    public AbstractBalloon(CosmeticUser user, CosmeticInfo info, boolean hasVariants) {
        super(user, info, hasVariants);
    }

    protected abstract void tick(final CosmeticUser user);
}
