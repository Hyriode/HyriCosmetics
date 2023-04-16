package fr.hyriode.cosmetics.gadget;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractGadget extends AbstractCosmetic {
    public AbstractGadget(CosmeticUser user, Cosmetics cosmetic) {
        super(user, cosmetic);
    }
}
