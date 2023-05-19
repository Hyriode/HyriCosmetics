package fr.hyriode.cosmetics.particle;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticInfo;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractParticle<T> extends AbstractCosmetic {

    public AbstractParticle(CosmeticUser user, CosmeticInfo info, boolean hasVariants) {
        super(user, info, hasVariants);
    }

    protected abstract void tick(final CosmeticUser user);


}
