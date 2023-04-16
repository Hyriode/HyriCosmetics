package fr.hyriode.cosmetics.mount;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractMount extends AbstractCosmetic {

    public AbstractMount(CosmeticUser user, Cosmetics cosmetic) {
        super(user, cosmetic);
    }
}
