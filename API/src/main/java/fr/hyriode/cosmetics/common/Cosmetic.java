package fr.hyriode.cosmetics.common;

import fr.hyriode.api.color.HyriChatColor;
import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.api.player.IHyriPlayer;
import fr.hyriode.api.rank.IHyriRankType;
import fr.hyriode.api.rank.PlayerRank;
import fr.hyriode.api.rank.StaffRank;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.CosmeticCategory.Default;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.utils.Head;
import fr.hyriode.cosmetics.utils.StringUtil;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Cosmetic {

    // == Particles ==
    ENCHANTED(Default.PARTICLE, "enchanted", CosmeticRarity.COMMON, -1, 5000, new ItemBuilder(Material.BOOK_AND_QUILL).build()),
    FIRE_INVOCATION(Default.PARTICLE, "fire_invocation", CosmeticRarity.RARE, 2000, 15000, new ItemBuilder(Material.LAVA_BUCKET).build()),
    STEP_IN_THE_AIR(Default.PARTICLE, "step_in_the_air", CosmeticRarity.EPIC, PlayerRank.VIP_PLUS, 1000, -1, new ItemBuilder(Material.FEATHER).build()),
    RAINBOW_TWINS(Default.PARTICLE, "rainbow_twins", CosmeticRarity.LEGENDARY, PlayerRank.EPIC, 3000, 50000, new ItemBuilder(Material.INK_SACK, 1, 2).build()),
    BLACK_VORTEX(Default.PARTICLE, "black_vortex", CosmeticRarity.STAFF, StaffRank.ADMINISTRATOR, -1, -1, new ItemBuilder(Material.RECORD_11).withAllItemFlags().build(), true),

    // == Pets ==
    SNOWMAN(Default.PET, "snowman", CosmeticRarity.RARE, PlayerRank.VIP_PLUS, 15000, 15000, Head.SNOWMAN_BODY.asItem()),
    MINI_ME(Default.PET, "mini_me", CosmeticRarity.COMMON, PlayerRank.PLAYER, 150, 15000, new ItemBuilder(Material.SKULL_ITEM).withData((short) 3).build()),
    ;

    private static final Map<CosmeticCategory, List<Cosmetic>> cosmetics = new HashMap<>();
    static {
        for (Cosmetic cosmetic : Cosmetic.values()) {
            if (!cosmetics.containsKey(cosmetic.getCategory())) {
                cosmetics.put(cosmetic.getCategory(), new ArrayList<>());
            }
            cosmetics.get(cosmetic.getCategory()).add(cosmetic);
        }
    }

    private final CosmeticCategory category;
    private final String id;
    private final CosmeticRarity rarity;
    private final IHyriRankType rank;
    private final int hyodesPrice;
    private final int hyrisPrice;
    private final ItemStack icon;
    private final boolean requireRank;

    Cosmetic(CosmeticCategory category, String id, CosmeticRarity rarity, IHyriRankType rank, int hyodesPrice, int hyrisPrice, ItemStack icon) {
        this.category = category;
        this.id = id;
        this.rarity = rarity;
        this.rank = rank;
        this.hyodesPrice = hyodesPrice;
        this.hyrisPrice = hyrisPrice;
        this.icon = icon;
        this.requireRank = false;
    }

    Cosmetic(CosmeticCategory category, String id, CosmeticRarity rarity, int hyodesPrice, int hyrisPrice, ItemStack icon) {
        this.category = category;
        this.id = id;
        this.rarity = rarity;
        this.rank = null;
        this.hyodesPrice = hyodesPrice;
        this.hyrisPrice = hyrisPrice;
        this.icon = icon;
        this.requireRank = false;
    }

    Cosmetic(CosmeticCategory category, String id, CosmeticRarity rarity, IHyriRankType rank, int hyodesPrice, int hyrisPrice, ItemStack icon, boolean requireRank) {
        this.category = category;
        this.id = id;
        this.rarity = rarity;
        this.rank = rank;
        this.hyodesPrice = hyodesPrice;
        this.hyrisPrice = hyrisPrice;
        this.icon = icon;
        this.requireRank = requireRank;
    }

    public CosmeticCategory getCategory() {
        return category;
    }

    public static List<CosmeticCategory> getCategories() {
        return new ArrayList<>(cosmetics.keySet());
    }

    public String getId() {
        return id;
    }

    public CosmeticRarity getRarity() {
        return rarity;
    }

    public IHyriRankType getRank() {
        return rank;
    }

    public int getHyodesPrice() {
        return hyodesPrice;
    }

    public int getHyrisPrice() {
        return hyrisPrice;
    }

    public ItemStack getIcon() {
        return icon.clone();
    }

    public static Map<CosmeticCategory, List<Cosmetic>> getCosmetics() {
        return cosmetics;
    }

    public HyriLanguageMessage getTranslatedName() {
        return HyriLanguageMessage.get("cosmetic." + category.getName() + "." + getId() + ".name");
    }

    public HyriLanguageMessage getTranslatedDescription() {
        return HyriLanguageMessage.get("cosmetic." + category.getName() + "." + getId() + ".description");
    }

    public boolean isBuyable() {
        return hyodesPrice != -1 || hyrisPrice != -1;
    }

    public boolean isRequireRank() {
        return requireRank;
    }

    public boolean hasRequiredRank(final Player player) {
        if (!requireRank) {
            return true;
        }
        return rank != null && HyriCosmetics.get().getUserProvider().getUser(player).asHyriPlayer().getRank().getPriority() > rank.getPriority();
    }

    private String name(Player player, String key) {
        return HyriLanguageMessage.get(key).getValue(player);
    }

    public ItemStack toItemStack(final Player player, boolean withAction) {
        final CosmeticUser user = HyriCosmetics.get().getUserProvider().getUser(player);
        final IHyriPlayer hyriPlayer = user.asHyriPlayer();
        final CosmeticCategory category = this.getCategory();

        final ItemBuilder builder = new ItemBuilder(icon.clone())
                .withName(getTranslatedName(player))
                .withLore(StringUtil.splitIntoPhrases(getTranslatedDescription(player), 35))
                .appendLore("")
                .appendLore(getRarityInfo(player));

        if (withAction) {
            String footer;
            if (!user.hasUnlockedCosmetic(this)) {
                footer = getUnlockInfo(player, user, hyriPlayer, builder);
            } else {
                footer = getEquipInfo(player, user, category, builder);
            }
            builder.appendLore("").appendLore(footer);
        }

        return builder.build();
    }

    private String getTranslatedName(final Player player) {
        return ChatColor.AQUA + getTranslatedName().getValue(player);
    }

    private String getTranslatedDescription(final Player player) {
        return getTranslatedDescription().getValue(player);
    }

    private String getRarityInfo(final Player player) {
        final String rarityColor = getRarity().getColor().toString();
        final String rarityName = HyriChatColor.BOLD + getRarity().getName().toUpperCase();
        final String rarityLabel = name(player, "gui.cosmetic.rarity") + ": ";
        return rarityLabel + rarityColor + rarityName;
    }

    private String getUnlockInfo(final Player player, final CosmeticUser user, final IHyriPlayer hyriPlayer, ItemBuilder builder) {
        String footer = "";
        if (!hasRequiredRank(player)) {
            footer = name(player, "gui.cosmetic.cant_unlock");
        } else if (isBuyable()){
            final String priceInfo = getPriceInfo(player, hyriPlayer, builder);
            builder.appendLore(priceInfo);
            footer = name(player, "gui.cosmetic.click_to_buy");
        }
        return footer;
    }

    private String getPriceInfo(final Player player, final IHyriPlayer hyriPlayer, ItemBuilder builder) {
        String priceInfo = "";
        if (isBuyable()) {
            builder.appendLore("");
            if (hyodesPrice > 0 && hyrisPrice > 0) {
                priceInfo = name(player, "gui.cosmetic.price_two_option")
                        .replace("%price1%", hyriPlayer.getHyris().getColor().toString() + hyrisPrice + " ⛃")
                        .replace("%price2%", hyriPlayer.getHyodes().getColor().toString() + hyodesPrice + " ✦");
            } else if (hyrisPrice > 0) {
                priceInfo = name(player, "gui.cosmetic.price_one_option")
                        .replace("%price1%", hyriPlayer.getHyris().getColor().toString() + hyrisPrice + " ⛃");
            } else if (hyodesPrice > 0) {
                priceInfo = name(player, "gui.cosmetic.price_one_option")
                        .replace("%price1%", hyriPlayer.getHyodes().getColor().toString() + hyodesPrice + " ✦");
            }
        }
        if (!isRequireRank() && rank != null && rank != PlayerRank.PLAYER) {
            builder.appendLore(name(player, "gui.cosmetic.offered_with_rank").replace("%rank%", rank.getDefaultPrefix()));
        }
        return priceInfo;
    }

    private String getEquipInfo(final Player player, final CosmeticUser user, final CosmeticCategory category, ItemBuilder builder) {
        String footer;
        if (user.hasEquippedCosmetic(category) && user.getEquippedCosmetic(category) == this) {
            if (!user.getEquippedCosmetics().get(category).getAbstractCosmetic().hasVariants()) {
                footer = name(player, "gui.cosmetic.already_equipped");
            } else {
                footer = name(player, "gui.cosmetic.click_to_edit");
            }
            builder.withGlow();
        } else {
            footer = name(player, "gui.cosmetic.click_to_equip");
        }
        return footer;
    }

}
