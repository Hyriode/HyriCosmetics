package fr.hyriode.cosmetics.particle;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractParticle extends AbstractCosmetic {

    public AbstractParticle(CosmeticUser user, Cosmetics cosmetic) {
        super(user, cosmetic);
    }

    protected abstract void tick(final CosmeticUser user);
}
