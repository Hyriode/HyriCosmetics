package fr.hyriode.cosmetics.balloon;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractBalloon extends AbstractCosmetic {

    public AbstractBalloon(CosmeticUser user, Cosmetics cosmetic) {
        super(user, cosmetic);
    }

    protected abstract void tick(final CosmeticUser user);

}
