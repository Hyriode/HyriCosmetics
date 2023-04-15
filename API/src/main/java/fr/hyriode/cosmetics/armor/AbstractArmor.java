package fr.hyriode.cosmetics.armor;

import fr.hyriode.api.rank.IHyriRankType;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.CosmeticRarity;
import fr.hyriode.cosmetics.common.Cosmetics;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractArmor extends AbstractCosmetic {

    public AbstractArmor(CosmeticUser user, Cosmetics cosmetic) {
        super(user, cosmetic);
    }
}
