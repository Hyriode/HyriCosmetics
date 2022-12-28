package fr.hyriode.cosmetics.mount;

import fr.hyriode.api.rank.type.IHyriRankType;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.CosmeticRarity;
import fr.hyriode.hyrame.item.ItemBuilder;

public abstract class AbstractMount extends AbstractCosmetic {
    public AbstractMount(String id, CosmeticRarity rarity, IHyriRankType requireRank, int tokenPrice, int hyrisPrice, ItemBuilder icon, CosmeticCategory category) {
        super(id, rarity, requireRank, tokenPrice, hyrisPrice, icon, category);
    }
}
