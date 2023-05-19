package fr.hyriode.cosmetics.pet;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticInfo;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.Location;

public abstract class AbstractPet<T> extends AbstractCosmetic<T> {

    public AbstractPet(CosmeticUser user, CosmeticInfo info, boolean hastVariants) {
        super(user, info, hastVariants);
    }

    protected abstract void tick(final CosmeticUser user);

    public abstract Location getReferenceLocation();

}
