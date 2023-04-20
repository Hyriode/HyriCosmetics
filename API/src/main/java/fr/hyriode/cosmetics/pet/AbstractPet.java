package fr.hyriode.cosmetics.pet;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.Cosmetic;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.Location;

public abstract class AbstractPet<T> extends AbstractCosmetic<T> {

    public AbstractPet(CosmeticUser user, Cosmetic cosmetic, boolean hastVariants) {
        super(user, cosmetic, hastVariants);
    }

    protected abstract void tick(final CosmeticUser user);

    public abstract Location getReferenceLocation();

}
