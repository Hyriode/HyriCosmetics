package fr.hyriode.cosmetics.common;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.api.rank.IHyriRankType;
import fr.hyriode.cosmetics.utils.Head;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface CosmeticInfo {
    CosmeticCategory getCategory();

    String getId();

    CosmeticRarity getRarity();

    IHyriRankType getRank();

    boolean isRequireRank();

    int getHyodesPrice();

    int getHyrisPrice();

    ItemStack getIcon();

    Head getHead();

    HyriLanguageMessage getTranslatedName();

    HyriLanguageMessage getTranslatedDescription();

    boolean isBuyable();

    boolean hasRequiredRank(Player player);

    boolean canBuyIt(Player player);

    boolean isAccessible(Player player);

    /*
    * Do not use this method
    * only usable by Cosmetic.java class
     */
    void setCosmetic(Cosmetic cosmetic);

    Cosmetic getCosmetic();
}
