package fr.hyriode.cosmetics.common;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.api.rank.IHyriRankType;
import fr.hyriode.cosmetics.transaction.CosmeticPrice;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.utils.Head;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface CosmeticInfo {

    CosmeticCategory getCategory();

    String getId();

    CosmeticRarity getRarity();

    IHyriRankType getRank();

    boolean isOnlyWithRank();

    CosmeticPrice getPrice();

    ItemStack getIcon();

    HyriLanguageMessage getTranslatedName();

    HyriLanguageMessage getTranslatedDescription();

    boolean isPurchasable();

    boolean hasRequiredRank(Player player);

    boolean canBuyIt(Player player);

    boolean isAccessible(Player player);

}
