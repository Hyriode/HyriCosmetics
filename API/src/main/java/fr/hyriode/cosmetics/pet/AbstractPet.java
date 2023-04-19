package fr.hyriode.cosmetics.pet;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.Location;

public abstract class AbstractPet extends AbstractCosmetic {

    public AbstractPet(CosmeticUser user, Cosmetic cosmetic) {
        super(user, cosmetic);
    }

    protected abstract void tick(final CosmeticUser user);

    public abstract Location getReferenceLocation();

}
