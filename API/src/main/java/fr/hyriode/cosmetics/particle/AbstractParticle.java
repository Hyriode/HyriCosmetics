package fr.hyriode.cosmetics.particle;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.common.CosmeticVariants;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractParticle<T> extends AbstractCosmetic implements CosmeticVariants<T> {

    public AbstractParticle(CosmeticUser user, Cosmetic cosmetic) {
        super(user, cosmetic);
    }

    protected abstract void tick(final CosmeticUser user);


}
