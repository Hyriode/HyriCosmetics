package fr.hyriode.cosmetics.common;

import fr.hyriode.api.rank.type.IHyriRankType;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.Location;

public abstract class AbstractCosmetic {

    private final String id;
    private final CosmeticType type;
    private final CosmeticRarity rarity;
    private final IHyriRankType requireRank;
    private final int tokenPrice;
    private final int hyrisPrice;
    private final ItemBuilder icon;
    private final CosmeticCategory category;

    public AbstractCosmetic(String id, CosmeticType type, CosmeticRarity rarity, IHyriRankType requireRank,
                            int tokenPrice, int hyrisPrice, ItemBuilder icon, CosmeticCategory category)
    {
        this.id = id;
        this.type = type;
        this.rarity = rarity;
        this.requireRank = requireRank;
        this.tokenPrice = tokenPrice;
        this.hyrisPrice = hyrisPrice;
        this.icon = icon;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public CosmeticType getType() {
        return type;
    }

    public CosmeticRarity getRarity() {
        return rarity;
    }

    public IHyriRankType getRequireRank() {
        return requireRank;
    }

    public int getTokenPrice() {
        return tokenPrice;
    }

    public int getHyrisPrice() {
        return hyrisPrice;
    }

    public ItemBuilder getIcon() {
        return icon;
    }

    protected abstract Location getLocation();

    public abstract void onEquip();

    public abstract void onUnequip();
}