package fr.hyriode.cosmetics.common;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.api.rank.IHyriRank;
import fr.hyriode.api.rank.IHyriRankType;
import fr.hyriode.api.rank.PlayerRank;
import fr.hyriode.api.rank.StaffRank;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.utils.Head;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class CosmeticInfoBuilder implements CosmeticInfo {

    private final CosmeticCategory category;
    private final String id;

    private Cosmetic cosmetic;

    private CosmeticRarity rarity = CosmeticRarity.COMMON;
    private IHyriRankType rank = PlayerRank.PLAYER;
    private boolean requireRank = false;
    private int hyodesPrice = -1;
    private int hyrisPrice = -1;
    private Function<Cosmetic, ItemStack> icon = cosmetic -> new ItemStack(Material.BARRIER);
    private Head head = Head.AMONG_US;

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

    public CosmeticInfoBuilder withRequireRank(final boolean requireRank) {
        this.requireRank = requireRank;
        return this;
    }

    @Override
    public boolean isRequireRank() {
        return this.requireRank;
    }

    public CosmeticInfoBuilder withHyodesPrice(final int hyodesPrice) {
        this.hyodesPrice = hyodesPrice;
        return this;
    }

    @Override
    public int getHyodesPrice() {
        return this.hyodesPrice;
    }

    public CosmeticInfoBuilder withHyrisPrice(final int hyrisPrice) {
        this.hyrisPrice = hyrisPrice;
        return this;
    }

    @Override
    public int getHyrisPrice() {
        return this.hyrisPrice;
    }

    public CosmeticInfoBuilder withIcon(final Function<Cosmetic, ItemStack> icon) {
        this.icon = icon;
        return this;
    }

    public CosmeticInfoBuilder withIcon(final ItemStack icon) {
        return withIcon(cosmetic -> new ItemBuilder(icon.clone()).withAllItemFlags().build());
    }

    public CosmeticInfoBuilder withIcon(final Head icon) {
        return withIcon(cosmetic -> new ItemBuilder(icon.asItem().clone()).withAllItemFlags().build());
    }

    public CosmeticInfoBuilder withIcon(final Material material) {
        return withIcon(cosmetic -> new ItemBuilder(material).withAllItemFlags().build());
    }

    @Override
    public ItemStack getIcon() {
        return this.icon.apply(cosmetic).clone();
    }

    public CosmeticInfoBuilder withHead(final Head head) {
        this.head = head;
        this.withIcon(head);
        return this;
    }

    @Override
    public Head getHead() {
        return this.head;
    }

    @Override
    public HyriLanguageMessage getTranslatedName() {
        return HyriLanguageMessage.get("cosmetic." + this.category.getName() + "." + this.id + ".name");
    }

    @Override
    public HyriLanguageMessage getTranslatedDescription() {
        return HyriLanguageMessage.get("cosmetic." + this.category.getName() + "." + this.id + ".description");
    }

    @Override
    public boolean isBuyable() {
        return hyodesPrice != -1 || hyrisPrice != -1;
    }

    @Override
    public boolean hasRequiredRank(final Player player) {
        final IHyriRank playerRank = HyriCosmetics.get().getUserProvider().getUser(player).asHyriPlayer().getRank();
        if (rank instanceof StaffRank && playerRank.isStaff()) {
            return playerRank.isSuperior((StaffRank) rank);
        } else if (rank instanceof PlayerRank) {
            return playerRank.isSuperior((PlayerRank) rank);
        }

        return false;
    }

    @Override
    public boolean canBuyIt(final Player player) {
        if (!this.requireRank) return true;
        return this.hasRequiredRank(player);
    }

    @Override
    public boolean isAccessible(final Player player) {
        return this.hasRequiredRank(player);
    }

    @Override
    public final void setCosmetic(final Cosmetic cosmetic) {
        this.cosmetic = cosmetic;
    }

    @Override
    public Cosmetic getCosmetic() {
        return this.cosmetic;
    }
}
