package fr.hyriode.cosmetics.gadget;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticInfo;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractGadget extends AbstractCosmetic {
    public AbstractGadget(CosmeticUser user, CosmeticInfo info, boolean hastVariants) {
        super(user, info, hastVariants);
    }
}
