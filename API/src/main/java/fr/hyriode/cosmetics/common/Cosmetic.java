package fr.hyriode.cosmetics.common;

import fr.hyriode.api.color.HyriChatColor;
import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.api.player.IHyriPlayer;
import fr.hyriode.api.rank.IHyriRank;
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
import java.util.function.Function;

public enum Cosmetic {

    // == Particles ==
    ENCHANTED(Default.PARTICLE, "enchanted", CosmeticRarity.COMMON, 350, 5000, c -> new ItemBuilder(Material.BOOK_AND_QUILL).build()),
    FIRE_INVOCATION(Default.PARTICLE, "fire_invocation", CosmeticRarity.RARE, 550, 10000, c -> new ItemBuilder(Material.LAVA_BUCKET).build()),
    GEM_INVOCATION(Default.PARTICLE, "gem_invocation", CosmeticRarity.RARE, 550, 10000, c -> new ItemBuilder(Material.EMERALD).build()),
    PORTAL_INVOCATION(Default.PARTICLE, "portal_invocation", CosmeticRarity.RARE, 550, 10000, c -> new ItemBuilder(Material.ENCHANTMENT_TABLE).build()),
    STEP_IN_THE_AIR(Default.PARTICLE, "step_in_the_air", CosmeticRarity.EPIC, PlayerRank.VIP_PLUS, 750, 25000, c -> new ItemBuilder(Material.FEATHER).build()),
    RAINBOW_TWINS(Default.PARTICLE, "rainbow_twins", CosmeticRarity.LEGENDARY, PlayerRank.EPIC, 1500, 50000, c -> new ItemBuilder(Material.INK_SACK, 1, 2).build()),
    BLACK_VORTEX(Default.PARTICLE, "black_vortex", CosmeticRarity.STAFF, StaffRank.ADMINISTRATOR, -1, -1, c -> new ItemBuilder(Material.RECORD_11).withAllItemFlags().build(), true),

    // == Pets ==
    MINI_ME(Default.PET, "mini_me", CosmeticRarity.COMMON, PlayerRank.PLAYER, 450, 7500, c -> new ItemBuilder(Material.SKULL_ITEM).withData((short) 3).build()),
    SNOWMAN(Default.PET, "snowman", CosmeticRarity.RARE, PlayerRank.VIP_PLUS, 750, 15000, c -> Head.SNOWMAN_BODY.asItem()),
    REAPER(Default.PET, "reaper", CosmeticRarity.EPIC, PlayerRank.VIP_PLUS, 750, 25000, c -> Head.REAPER.asItem()),
    GHOST(Default.PET, "ghost", CosmeticRarity.EPIC, PlayerRank.VIP_PLUS, 750, 25000, c -> Head.GHOST.asItem()),

    // == Balloons ==
    HYRIODE_BALLOON(Default.BALLOON, "hyriode", CosmeticRarity.EXCLUSIVE, -1, -1, c -> c.headTexture.asItem(), Head.HYRIODE),

    TWITCH_BALLOON(Default.BALLOON, "twitch", CosmeticRarity.RARE, -1, -1, c -> c.headTexture.asItem(), Head.TWITCH),
    YOUTUBE_BALLOON(Default.BALLOON, "youtube", CosmeticRarity.RARE, -1, -1, c -> c.headTexture.asItem(), Head.YOUTUBE),
    TIKTOK_BALLOON(Default.BALLOON, "tiktok", CosmeticRarity.RARE, -1, -1, c -> c.headTexture.asItem(), Head.TIKTOK),

    YELLOW_BALLOON(Default.BALLOON, "yellow", CosmeticRarity.COMMON, -1, -1, c -> c.headTexture.asItem(), Head.BALLOON_COLOR_YELLOW),
    RED_BALLOON(Default.BALLOON, "red", CosmeticRarity.COMMON, -1, -1, c -> c.headTexture.asItem(), Head.BALLOON_COLOR_RED),
    BLUE_BALLOON(Default.BALLOON, "blue", CosmeticRarity.COMMON, -1, -1, c -> c.headTexture.asItem(), Head.BALLOON_COLOR_BLUE),
    GREEN_BALLOON(Default.BALLOON, "green", CosmeticRarity.COMMON, -1, -1, c -> c.headTexture.asItem(), Head.BALLOON_COLOR_GREEN),
    PURPLE_BALLOON(Default.BALLOON, "purple", CosmeticRarity.COMMON, -1, -1, c -> c.headTexture.asItem(), Head.BALLOON_COLOR_PURPLE),
    ORANGE_BALLOON(Default.BALLOON, "orange", CosmeticRarity.COMMON, -1, -1, c -> c.headTexture.asItem(), Head.BALLOON_COLOR_ORANGE),
    PINK_BALLOON(Default.BALLOON, "pink", CosmeticRarity.COMMON, -1, -1, c -> c.headTexture.asItem(), Head.BALLOON_COLOR_PINK),
    LIGHT_BLUE_BALLOON(Default.BALLOON, "white", CosmeticRarity.COMMON, -1, -1, c -> c.headTexture.asItem(), Head.BALLOON_COLOR_LIGHT_BLUE),
    LIME_BALLOON(Default.BALLOON, "black", CosmeticRarity.COMMON, -1, -1, c -> c.headTexture.asItem(), Head.BALLOON_COLOR_LIME),
    GRAY_BALLOON(Default.BALLOON, "gray", CosmeticRarity.COMMON, -1, -1, c -> c.headTexture.asItem(), Head.BALLOON_COLOR_GRAY),
    DARK_BLUE_BALLOON(Default.BALLOON, "dark_blue", CosmeticRarity.COMMON, -1, -1, c -> c.headTexture.asItem(), Head.BALLOON_COLOR_DARK_BLUE),

    BEACH_BALLOON(Default.BALLOON, "beach", CosmeticRarity.RARE, -1, 2000, c -> c.headTexture.asItem(), Head.BEACH),

    JACK_O_LANTERN_YELLOW_BALLOON(Default.BALLOON, "jack_o_lantern_yellow", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.JACK_O_LANTERN_YELLOW),
    JACK_O_LANTERN_LIGHT_BLUE_BALLOON(Default.BALLOON, "jack_o_lantern_light_blue", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.JACK_O_LANTERN_LIGHT_BLUE),
    JACK_O_LANTERN_BLUE_BALLOON(Default.BALLOON, "jack_o_lantern_blue", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.JACK_O_LANTERN_BLUE),
    JACK_O_LANTERN_LIME_BALLOON(Default.BALLOON, "jack_o_lantern_lime", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.JACK_O_LANTERN_LIME),
    JACK_O_LANTERN_GREEN_BALLOON(Default.BALLOON, "jack_o_lantern_green", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.JACK_O_LANTERN_GREEN),
    JACK_O_LANTERN_ORANGE_BALLOON(Default.BALLOON, "jack_o_lantern_orange", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.JACK_O_LANTERN_ORANGE),
    JACK_O_LANTERN_PINK_BALLOON(Default.BALLOON, "jack_o_lantern_pink", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.JACK_O_LANTERN_PINK),
    JACK_O_LANTERN_PURPLE_BALLOON(Default.BALLOON, "jack_o_lantern_purple", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.JACK_O_LANTERN_PURPLE),
    JACK_O_LANTERN_RED_BALLOON(Default.BALLOON, "jack_o_lantern_red", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.JACK_O_LANTERN_RED),
    JACK_O_LANTERN_GRAY_BALLOON(Default.BALLOON, "jack_o_lantern_gray", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.JACK_O_LANTERN_GRAY),
    JACK_O_LANTERN_CYAN_BALLOON(Default.BALLOON, "jack_o_lantern_cyan", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.JACK_O_LANTERN_CYAN),
    JACK_O_LANTERN_MAGENTA_BALLOON(Default.BALLOON, "jack_o_lantern_magenta", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.JACK_O_LANTERN_MAGENTA),
    JACK_O_LANTERN_BLACK_BALLOON(Default.BALLOON, "jack_o_lantern_black", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.JACK_O_LANTERN_BLACK),
    JACK_O_LANTERN_WHITE_BALLOON(Default.BALLOON, "jack_o_lantern_white", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.JACK_O_LANTERN_WHITE),
    JACK_O_LANTERN_BROWN_BALLOON(Default.BALLOON, "jack_o_lantern_brown", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.JACK_O_LANTERN_BROWN),

    AMONG_US_BALLOON(Default.BALLOON, "among_us", CosmeticRarity.LEGENDARY, -1, 5000, c -> c.headTexture.asItem(), Head.AMONG_US),

    MOON_FULL_BALLOON(Default.BALLOON, "moon_full", CosmeticRarity.RARE, -1, -1, c -> c.headTexture.asItem(), Head.MOON_FULL),
    MOON_HALF_BALLOON(Default.BALLOON, "moon_half", CosmeticRarity.RARE, -1, -1, c -> c.headTexture.asItem(), Head.MOON_HALF),
    MOON_NEW_BALLOON(Default.BALLOON, "moon_new", CosmeticRarity.RARE, -1, -1, c -> c.headTexture.asItem(), Head.MOON_NEW),

    SUN_BALLOON(Default.BALLOON, "sun", CosmeticRarity.EPIC, -1, 2500, c -> c.headTexture.asItem(), Head.SUN),

    PRESENT_WHITE_BALLOON(Default.BALLOON, "present_white", CosmeticRarity.RARE, -1, -1, c -> c.headTexture.asItem(), Head.PRESENT_WHITE),
    PRESENT_GREEN_BALLOON(Default.BALLOON, "present_green", CosmeticRarity.RARE, -1, -1, c -> c.headTexture.asItem(), Head.PRESENT_GREEN),
    PRESENT_BLUE_BALLOON(Default.BALLOON, "present_blue", CosmeticRarity.RARE, -1, -1, c -> c.headTexture.asItem(), Head.PRESENT_BLUE),
    PRESENT_PURPLE_BALLOON(Default.BALLOON, "present_purple", CosmeticRarity.RARE, -1, -1, c -> c.headTexture.asItem(), Head.PRESENT_PURPLE),
    PRESENT_YELLOW_BALLOON(Default.BALLOON, "present_yellow", CosmeticRarity.RARE, -1, -1, c -> c.headTexture.asItem(), Head.PRESENT_YELLOW),

    RUBIKS_CUBE_BALLOON(Default.BALLOON, "rubiks_cube", CosmeticRarity.EPIC, -1, -1, c -> c.headTexture.asItem(), Head.RUBIKS_CUBE),
    TOTEM_BALLOON(Default.BALLOON, "totem", CosmeticRarity.LEGENDARY, -1, -1, c -> c.headTexture.asItem(), Head.TOTEM),
    POOP_BALLOON(Default.BALLOON, "poop", CosmeticRarity.LEGENDARY, -1, -1, c -> c.headTexture.asItem(), Head.POOP),
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
    private final Function<Cosmetic, ItemStack> icon;
    private final boolean requireRank;
    private final Head headTexture;

    Cosmetic(CosmeticCategory category, String id, CosmeticRarity rarity, IHyriRankType rank, int hyodesPrice, int hyrisPrice, Function<Cosmetic, ItemStack> icon) {
        this.category = category;
        this.id = id;
        this.rarity = rarity;
        this.rank = rank;
        this.hyodesPrice = hyodesPrice;
        this.hyrisPrice = hyrisPrice;
        this.icon = icon;
        this.requireRank = false;
        this.headTexture = Head.SNOWMAN_BODY;
    }

    Cosmetic(CosmeticCategory category, String id, CosmeticRarity rarity, int hyodesPrice, int hyrisPrice, Function<Cosmetic, ItemStack> icon) {
        this.category = category;
        this.id = id;
        this.rarity = rarity;
        this.rank = null;
        this.hyodesPrice = hyodesPrice;
        this.hyrisPrice = hyrisPrice;
        this.icon = icon;
        this.requireRank = false;
        this.headTexture = Head.SNOWMAN_BODY;
    }

    Cosmetic(CosmeticCategory category, String id, CosmeticRarity rarity, int hyodesPrice, int hyrisPrice, Function<Cosmetic, ItemStack> icon, Head head) {
        this.category = category;
        this.id = id;
        this.rarity = rarity;
        this.rank = null;
        this.hyodesPrice = hyodesPrice;
        this.hyrisPrice = hyrisPrice;
        this.icon = icon;
        this.requireRank = false;
        this.headTexture = head;
    }

    Cosmetic(CosmeticCategory category, String id, CosmeticRarity rarity, IHyriRankType rank, int hyodesPrice, int hyrisPrice, Function<Cosmetic, ItemStack> icon, boolean requireRank) {
        this.category = category;
        this.id = id;
        this.rarity = rarity;
        this.rank = rank;
        this.hyodesPrice = hyodesPrice;
        this.hyrisPrice = hyrisPrice;
        this.icon = icon;
        this.requireRank = requireRank;
        this.headTexture = Head.SNOWMAN_BODY;
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
        return icon.apply(this).clone();
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


    public Head getTexture() {
        return headTexture;
    }

    public boolean isRequireRank() {
        return requireRank;
    }

    private boolean hasRequiredRank(final Player player) {
        final IHyriRank playerRank = HyriCosmetics.get().getUserProvider().getUser(player).asHyriPlayer().getRank();
        if (playerRank.isStaff() && rank instanceof StaffRank) {
            return playerRank.isSuperior((StaffRank) rank);
        } else if (playerRank.isDefault() && rank instanceof PlayerRank) {
            return playerRank.isSuperior((PlayerRank) rank);
        }

        return false;
    }

    public boolean canBuyIt(final Player player) {
        if (!this.requireRank) return true;
        return this.hasRequiredRank(player);
    }
    
    public boolean isAccessible(final Player player) {
        return this.hasRequiredRank(player);
    }

    private String name(Player player, String key) {
        return HyriLanguageMessage.get(key).getValue(player);
    }

    public ItemStack toItemStack(final Player player, boolean withAction) {
        final CosmeticUser user = HyriCosmetics.get().getUserProvider().getUser(player);
        final IHyriPlayer hyriPlayer = user.asHyriPlayer();
        final CosmeticCategory category = this.getCategory();

        final ItemBuilder builder = new ItemBuilder(user.hasUnlockedCosmetic(this) ? getIcon() : new ItemStack(Material.INK_SACK, 1, (short) 8))
                .withName(getTranslatedName(player))
                .withLore(StringUtil.splitIntoPhrases(getTranslatedDescription(player), 35))
                .appendLore("")
                .appendLore(getRarityInfo(player));

        if (withAction) {
            String footer;
            if (!user.hasUnlockedCosmetic(this)) {
                footer = getUnlockInfo(player, hyriPlayer, builder);
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
        final String rarityName = HyriChatColor.BOLD + getRarity().getTranslatedName(player).toUpperCase();
        final String rarityLabel = name(player, "gui.cosmetic.rarity") + ": ";
        return rarityLabel + rarityColor + rarityName;
    }

    private String getUnlockInfo(final Player player, final IHyriPlayer hyriPlayer, ItemBuilder builder) {
        String footer;
        if (isBuyable() && canBuyIt(player)) {
            final String priceInfo = getPriceInfo(player, hyriPlayer, builder);
            builder.appendLore(priceInfo);
            footer = name(player, "gui.cosmetic.click_to_buy");
        } else {
            footer = name(player, "gui.cosmetic.cant_unlock");
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
