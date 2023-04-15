package fr.hyriode.cosmetics.pet;

import fr.hyriode.api.rank.IHyriRankType;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.CosmeticRarity;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractPet extends AbstractCosmetic {

    public AbstractPet(CosmeticUser user, Cosmetics cosmetic) {
        super(user, cosmetic);
    }
}
