package fr.hyriode.cosmetics.common;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.api.rank.type.IHyriRankType;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class AbstractCosmetic {

    private final String id;
    private final CosmeticRarity rarity;
    private final IHyriRankType requireRank;
    private final int tokenPrice;
    private final int hyrisPrice;
    private final ItemBuilder icon;
    private final CosmeticCategory category;

    public AbstractCosmetic(String id, CosmeticRarity rarity, IHyriRankType requireRank,
                            int tokenPrice, int hyrisPrice, ItemBuilder icon, CosmeticCategory category)
    {
        this.id = id;
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
        return new ItemBuilder(this.icon.build().clone());
    }

    public abstract void onEquip(final CosmeticUser user);

    public abstract void onUnequip(final CosmeticUser user);

    public CosmeticCategory getCategory() {
        return category;
    }

    public HyriLanguageMessage getTranslatedName() {
        return HyriLanguageMessage.get("cosmetic." + category.getName() + "." + getId() + ".name");
    }

    public HyriLanguageMessage getTranslatedDescription() {
        return HyriLanguageMessage.get("cosmetic." + category.getName() + "." + getId() + ".description");
    }
}
