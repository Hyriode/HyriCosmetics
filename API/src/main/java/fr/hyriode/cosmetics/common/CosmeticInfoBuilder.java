package fr.hyriode.cosmetics.common;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.api.rank.IHyriRank;
import fr.hyriode.api.rank.IHyriRankType;
import fr.hyriode.api.rank.PlayerRank;
import fr.hyriode.api.rank.StaffRank;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.transaction.CosmeticPrice;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.utils.Head;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class CosmeticInfoBuilder implements CosmeticInfo {

    private final CosmeticCategory category;
    private final String id;

    private CosmeticRarity rarity = CosmeticRarity.COMMON;

    private IHyriRankType rank = null;
    private boolean onlyWithRank = false;

    private CosmeticPrice price = null;
    private ItemStack icon = new ItemStack(Material.BARRIER);

    public CosmeticInfoBuilder(CosmeticCategory category, String id) {
        this.category = category;
        this.id = id;
    }

    @Override
    public CosmeticCategory getCategory() {
        return this.category;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public CosmeticInfoBuilder withRarity(final CosmeticRarity rarity) {
        this.rarity = rarity;
        return this;
    }

    @Override
    public CosmeticRarity getRarity() {
        return this.rarity;
    }

    public CosmeticInfoBuilder withRank(final IHyriRankType rank) {
        this.rank = rank;
        return this;
    }

    @Override
    public IHyriRankType getRank() {
        return this.rank;
    }

    public CosmeticInfoBuilder onlyWithRank() {
        this.onlyWithRank = true;
        return this;
    }

    @Override
    public boolean isOnlyWithRank() {
        return this.onlyWithRank;
    }

    @Override
    public CosmeticPrice getPrice() {
        return this.price == null ? this.category.getDefaultPrice(this.rarity) : this.price;
    }

    public CosmeticInfoBuilder withPrice(CosmeticPrice price) {
        this.price = price;
        return this;
    }

    public CosmeticInfoBuilder withPrice(CosmeticPrice.Currency currency, int value) {
        if (this.price == null) {
            this.price = new CosmeticPrice();
        }

        this.price.newValue(currency, value);
        return this;
    }

    public CosmeticInfoBuilder withIcon(final ItemStack icon) {
        this.icon = new ItemBuilder(icon).withAllItemFlags().build();
        return this;
    }

    public CosmeticInfoBuilder withIcon(final Head icon) {
        this.icon = new ItemBuilder(icon.asItem()).withAllItemFlags().build();
        return this;
    }

    public CosmeticInfoBuilder withIcon(final Material material) {
        return this.withIcon(new ItemStack(material));
    }

    @Override
    public ItemStack getIcon() {
        return this.icon.clone();
    }

    @Override
    public HyriLanguageMessage getTranslatedName() {
        return HyriLanguageMessage.get("cosmetic." + this.category.getName() + "." + this.id + ".name");
    }

    @Override
    public HyriLanguageMessage getTranslatedDescription() {
        return HyriLanguageMessage.get("cosmetic." + this.category.getName() + "." + this.id + ".description");
    }

    public CosmeticInfoBuilder notPurchasable() {
        this.price = new CosmeticPrice();
        return this;
    }

    @Override
    public boolean isPurchasable() {
        return !this.getPrice().empty();
    }

    @Override
    public boolean hasRequiredRank(final Player player) {
        final IHyriRank playerRank = HyriCosmetics.get().getUserProvider().getUser(player).asHyriPlayer().getRank();

        if (this.rank instanceof StaffRank && playerRank.isStaff()) {
            return playerRank.isSuperior((StaffRank) this.rank);
        } else if (this.rank  instanceof PlayerRank) {
            return playerRank.isSuperior((PlayerRank) this.rank);
        }
        return false;
    }

    @Override
    public boolean canBuyIt(final Player player) {
        if (!this.onlyWithRank) {
            return true;
        }
        return this.hasRequiredRank(player);
    }

    @Override
    public boolean isAccessible(final Player player) {
        return this.hasRequiredRank(player);
    }

}
