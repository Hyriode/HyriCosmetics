package fr.hyriode.cosmetics.common;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.api.rank.IHyriRankType;
import fr.hyriode.cosmetics.user.CosmeticUser;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class AbstractCosmetic {

    private final CosmeticUser user;
    private final Cosmetic cosmetic;

    public AbstractCosmetic(CosmeticUser user, Cosmetic cosmetic) {
        this.user = user;
        this.cosmetic = cosmetic;
    }

    public String getId() {
        return cosmetic.getId();
    }

    public CosmeticRarity getRarity() {
        return cosmetic.getRarity();
    }

    public IHyriRankType getRequireRank() {
        return cosmetic.getRequireRank();
    }

    public int getHyodesPrice() {
        return cosmetic.getHyodesPrice();
    }

    public int getHyrisPrice() {
        return cosmetic.getHyrisPrice();
    }

    public ItemStack getIcon() {
        return cosmetic.getIcon().clone();
    }

    public abstract void onEquip(final CosmeticUser user);

    public abstract void onUnequip(final CosmeticUser user);

    public CosmeticCategory getCategory() {
        return cosmetic.getCategory();
    }
    public HyriLanguageMessage getTranslatedName() {
        return cosmetic.getTranslatedName();
    }

    public HyriLanguageMessage getTranslatedDescription() {
        return cosmetic.getTranslatedDescription();
    }

    public CosmeticUser getUser() {
        return user;
    }

    public Cosmetic getType() {
        return cosmetic;
    }

    public boolean isMoving() {
        return user.isMoving();
    }

    public Location getLocation() {
        return getPlayer().getLocation();
    }

    public Player getPlayer() {
        return user.asBukkit();
    }

    public boolean hasVariants() {
        return this instanceof CosmeticVariants<?>;
    }
}
