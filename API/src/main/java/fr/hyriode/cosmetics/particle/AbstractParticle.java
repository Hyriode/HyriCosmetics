package fr.hyriode.cosmetics.particle;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractParticle<T> extends AbstractCosmetic {

    public AbstractParticle(CosmeticUser user, Cosmetic cosmetic, boolean hasVariants) {
        super(user, cosmetic, hasVariants);
    }

    protected abstract void tick(final CosmeticUser user);


}
