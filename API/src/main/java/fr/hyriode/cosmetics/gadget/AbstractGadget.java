package fr.hyriode.cosmetics.gadget;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractGadget extends AbstractCosmetic {
    public AbstractGadget(CosmeticUser user, Cosmetic cosmetic) {
        super(user, cosmetic);
    }
}
