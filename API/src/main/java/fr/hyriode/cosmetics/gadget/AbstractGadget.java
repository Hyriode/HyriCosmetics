package fr.hyriode.cosmetics.gadget;

import fr.hyriode.api.rank.IHyriRankType;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.CosmeticRarity;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractGadget extends AbstractCosmetic {
    public AbstractGadget(CosmeticUser user, Cosmetics cosmetic) {
        super(user, cosmetic);
    }
}
