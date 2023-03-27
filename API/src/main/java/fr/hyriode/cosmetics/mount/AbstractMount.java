package fr.hyriode.cosmetics.mount;

import fr.hyriode.api.rank.IHyriRankType;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.CosmeticRarity;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractMount extends AbstractCosmetic {
    public AbstractMount(String id, CosmeticRarity rarity, IHyriRankType requireRank, int tokenPrice, int hyrisPrice, ItemStack icon) {
        super(id, rarity, requireRank, tokenPrice, hyrisPrice, icon, CosmeticCategory.Default.MOUNT);
    }
}
