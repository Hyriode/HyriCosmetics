package fr.hyriode.cosmetics.common;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.api.rank.IHyriRankType;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.hyrame.utils.Pair;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractCosmetic<T> implements CosmeticVariants<T> {

    private final CosmeticUser user;
    private final Cosmetic cosmetic;

    protected String variant;

    protected final Map<String, Pair<ItemStack, T>> variants;
    private final boolean hasVariants;

    public AbstractCosmetic(CosmeticUser user, Cosmetic cosmetic, boolean hasVariants) {
        this.user = user;
        this.cosmetic = cosmetic;

        this.variant = getDefaultVariant();
        this.variants = initVariants();
        this.hasVariants = hasVariants;
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

    @Override
    public Map<String, ItemStack> getVariantsItem() {
        return variants.entrySet().stream().collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue().getKey()), Map::putAll);
    }

    @Override
    public Map<String, T> getVariants() {
        return variants.entrySet().stream().collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue().getValue()), Map::putAll);
    }

    @Override
    public T getVariant(String variant) {
        return variants.get(variant).getValue();
    }

    @Override
    public boolean hasVariants() {
        return hasVariants;
    }

    @Override
    public String getDefaultVariant() {
        return null;
    }

    @Override
    public void updateVariant() {}

    @Override
    public Map<String, Pair<ItemStack, T>> initVariants() {
        return Collections.emptyMap();
    }

    @Override
    public void setVariant(String variant) {
        this.variant = variant;
        this.updateVariant();
    }

    @Override
    public String getVariant() {
        return this.variant;
    }
}
