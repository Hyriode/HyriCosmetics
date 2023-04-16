package fr.hyriode.cosmetics.armor;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;

public abstract class AbstractArmor extends AbstractCosmetic {

    public AbstractArmor(CosmeticUser user, Cosmetics cosmetic) {
        super(user, cosmetic);
    }
}
