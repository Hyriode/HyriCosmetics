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

    protected Map<String, Pair<ItemStack, T>> variants;
    private final boolean hasVariants;

    public AbstractCosmetic(CosmeticUser user, Cosmetic cosmetic, boolean hasVariants) {
        this.user = user;
        this.cosmetic = cosmetic;

        this.variant = getDefaultVariant();
        this.reloadVariants();
        this.hasVariants = hasVariants;
    }

    public String getId() {
        return cosmetic.getInfo().getId();
    }

    public abstract void onEquip(final CosmeticUser user);

    public abstract void onUnequip(final CosmeticUser user);

    public CosmeticCategory getCategory() {
        return cosmetic.getInfo().getCategory();
    }

    public HyriLanguageMessage getTranslatedName() {
        return cosmetic.getInfo().getTranslatedName();
    }

    public HyriLanguageMessage getTranslatedDescription() {
        return cosmetic.getInfo().getTranslatedDescription();
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
    public void reloadVariants() {
        this.variants = this.initVariants();
    }

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
