package fr.hyriode.cosmetics.common;

import fr.hyriode.api.color.HyriChatColor;
import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.api.player.IHyriPlayer;
import fr.hyriode.api.rank.PlayerRank;
import fr.hyriode.api.rank.StaffRank;
import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.CosmeticCategory.Default;
import fr.hyriode.cosmetics.user.CosmeticUser;
import fr.hyriode.cosmetics.utils.Head;
import fr.hyriode.cosmetics.utils.StringUtil;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Cosmetic {

    // == Particles ==
    ENCHANTED(new CosmeticInfoBuilder(Default.PARTICLE, "enchanted").withHyodesPrice(350).withHyrisPrice(5000)
            .withIcon(Material.BOOK_AND_QUILL)),
    FIRE_INVOCATION(new CosmeticInfoBuilder(Default.PARTICLE, "fire_invocation").withRarity(CosmeticRarity.RARE)
            .withHyodesPrice(550).withHyrisPrice(10000).withIcon(Material.LAVA_BUCKET)),
    GEM_INVOCATION(new CosmeticInfoBuilder(Default.PARTICLE, "gem_invocation").withRarity(CosmeticRarity.RARE)
            .withHyodesPrice(550).withHyrisPrice(10000).withIcon(Material.EMERALD)),
    PORTAL_INVOCATION(new CosmeticInfoBuilder(Default.PARTICLE, "portal_invocation").withRarity(CosmeticRarity.RARE)
            .withHyodesPrice(550).withHyrisPrice(10000).withIcon(Material.ENCHANTMENT_TABLE)),
    STEP_IN_THE_AIR(new CosmeticInfoBuilder(Default.PARTICLE, "step_in_the_air").withRarity(CosmeticRarity.EPIC)
            .withRank(PlayerRank.VIP_PLUS).withHyodesPrice(750).withHyrisPrice(25000).withIcon(Material.FEATHER)),
    RAINBOW_TWINS(new CosmeticInfoBuilder(Default.PARTICLE, "rainbow_twins").withRarity(CosmeticRarity.LEGENDARY)
            .withRank(PlayerRank.EPIC).withHyodesPrice(1500).withHyrisPrice(50000)
            .withIcon(new ItemStack(Material.INK_SACK, 1, (short) 2))),
    BLACK_VORTEX(new CosmeticInfoBuilder(Default.PARTICLE, "black_vortex").withRarity(CosmeticRarity.STAFF)
            .withRank(StaffRank.ADMINISTRATOR).withRequireRank(true).withIcon(Material.RECORD_11)),


    // == Pets ==
    MINI_ME(new CosmeticInfoBuilder(Default.PET, "mini_me").withHyodesPrice(450).withHyrisPrice(7500)
            .withIcon(new ItemBuilder(Material.SKULL_ITEM).withData((short) 3).build())),
    SNOWMAN(new CosmeticInfoBuilder(Default.PET, "snowman").withRarity(CosmeticRarity.RARE)
            .withRank(PlayerRank.VIP_PLUS).withHyodesPrice(750).withHyrisPrice(25000).withIcon(Head.SNOWMAN_HEAD)),
    REAPER(new CosmeticInfoBuilder(Default.PET, "reaper").withRarity(CosmeticRarity.EPIC)
            .withRank(PlayerRank.VIP_PLUS).withHyodesPrice(750).withHyrisPrice(25000).withIcon(Head.REAPER)),
    GHOST(new CosmeticInfoBuilder(Default.PET, "ghost").withRarity(CosmeticRarity.EPIC)
            .withRank(PlayerRank.VIP_PLUS).withHyodesPrice(750).withHyrisPrice(25000).withIcon(Head.GHOST)),


    // == Balloons ==
    YELLOW_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "yellow").withHead(Head.BALLOON_COLOR_YELLOW)),
    RED_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "red").withHead(Head.BALLOON_COLOR_RED)),
    BLUE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "blue").withHead(Head.BALLOON_COLOR_BLUE)),
    GREEN_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "green").withHead(Head.BALLOON_COLOR_GREEN)),
    PURPLE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "purple").withHead(Head.BALLOON_COLOR_PURPLE)),
    ORANGE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "orange").withHead(Head.BALLOON_COLOR_ORANGE)),
    PINK_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "pink").withHead(Head.BALLOON_COLOR_PINK)),
    LIGHT_BLUE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "white").withHead(Head.BALLOON_COLOR_LIGHT_BLUE)),
    LIME_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "black").withHead(Head.BALLOON_COLOR_LIME)),
    GRAY_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "gray").withHead(Head.BALLOON_COLOR_GRAY)),
    DARK_BLUE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "dark_blue").withHead(Head.BALLOON_COLOR_DARK_BLUE)),

    HYRIODE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "hyriode").withHead(Head.HYRIODE)
            .withRarity(CosmeticRarity.EXCLUSIVE)),
    TWITCH_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "twitch").withHead(Head.TWITCH)
            .withRarity(CosmeticRarity.RARE)),
    YOUTUBE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "youtube").withHead(Head.YOUTUBE)
            .withRarity(CosmeticRarity.RARE)),
    TIKTOK_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "tiktok").withHead(Head.TIKTOK)
            .withRarity(CosmeticRarity.RARE)),
    TWITTER_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "twitter").withHead(Head.TWITTER)
            .withRarity(CosmeticRarity.RARE)),
    BEACH_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "beach").withHead(Head.BEACH)
            .withRarity(CosmeticRarity.RARE)),
    AMONG_US_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "among_us").withHead(Head.AMONG_US)
            .withRarity(CosmeticRarity.LEGENDARY).withHyrisPrice(5000)),
    PRESENT_WHITE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "present_white").withHead(Head.PRESENT_WHITE)
            .withRarity(CosmeticRarity.RARE)),
    PRESENT_GREEN_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "present_green").withHead(Head.PRESENT_GREEN)
            .withRarity(CosmeticRarity.RARE)),
    PRESENT_BLUE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "present_blue").withHead(Head.PRESENT_BLUE)
            .withRarity(CosmeticRarity.RARE)),
    PRESENT_PURPLE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "present_purple").withHead(Head.PRESENT_PURPLE)
            .withRarity(CosmeticRarity.RARE)),
    PRESENT_YELLOW_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "present_yellow").withHead(Head.PRESENT_YELLOW)
            .withRarity(CosmeticRarity.RARE)),
    MOON_FULL_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "moon_full").withHead(Head.MOON_FULL).
            withRarity(CosmeticRarity.RARE)),
    MOON_HALF_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "moon_half").withHead(Head.MOON_HALF)
            .withRarity(CosmeticRarity.RARE)),
    MOON_NEW_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "moon_new").withHead(Head.MOON_NEW)
            .withRarity(CosmeticRarity.RARE)),
    SUN_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "sun").withHead(Head.SUN)
            .withRarity(CosmeticRarity.EPIC)),
    RUBIKS_CUBE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "rubiks_cube").withHead(Head.RUBIKS_CUBE)
            .withRarity(CosmeticRarity.EPIC)),
    TOTEM_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "totem").withHead(Head.TOTEM)
            .withRarity(CosmeticRarity.LEGENDARY)),
    POOP_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "poop").withHead(Head.POOP)
            .withRarity(CosmeticRarity.LEGENDARY)),

    JACK_O_LANTERN_YELLOW_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "jack_o_lantern_yellow")
            .withHead(Head.JACK_O_LANTERN_YELLOW).withRarity(CosmeticRarity.EPIC)),
    JACK_O_LANTERN_LIGHT_BLUE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "jack_o_lantern_light_blue")
            .withHead(Head.JACK_O_LANTERN_LIGHT_BLUE).withRarity(CosmeticRarity.EPIC)),
    JACK_O_LANTERN_BLUE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "jack_o_lantern_blue")
            .withHead(Head.JACK_O_LANTERN_BLUE).withRarity(CosmeticRarity.EPIC)),
    JACK_O_LANTERN_LIME_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "jack_o_lantern_lime")
            .withHead(Head.JACK_O_LANTERN_LIME).withRarity(CosmeticRarity.EPIC)),
    JACK_O_LANTERN_GREEN_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "jack_o_lantern_green")
            .withHead(Head.JACK_O_LANTERN_GREEN).withRarity(CosmeticRarity.EPIC)),
    JACK_O_LANTERN_ORANGE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "jack_o_lantern_orange")
            .withHead(Head.JACK_O_LANTERN_ORANGE).withRarity(CosmeticRarity.EPIC)),
    JACK_O_LANTERN_PINK_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "jack_o_lantern_pink")
            .withHead(Head.JACK_O_LANTERN_PINK).withRarity(CosmeticRarity.EPIC)),
    JACK_O_LANTERN_PURPLE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "jack_o_lantern_purple")
            .withHead(Head.JACK_O_LANTERN_PURPLE).withRarity(CosmeticRarity.EPIC)),
    JACK_O_LANTERN_RED_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "jack_o_lantern_red")
            .withHead(Head.JACK_O_LANTERN_RED).withRarity(CosmeticRarity.EPIC)),
    JACK_O_LANTERN_GRAY_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "jack_o_lantern_gray")
            .withHead(Head.JACK_O_LANTERN_GRAY).withRarity(CosmeticRarity.EPIC)),
    JACK_O_LANTERN_CYAN_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "jack_o_lantern_cyan")
            .withHead(Head.JACK_O_LANTERN_CYAN).withRarity(CosmeticRarity.EPIC)),
    JACK_O_LANTERN_MAGENTA_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "jack_o_lantern_magenta")
            .withHead(Head.JACK_O_LANTERN_MAGENTA).withRarity(CosmeticRarity.EPIC)),
    JACK_O_LANTERN_BLACK_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "jack_o_lantern_black")
            .withHead(Head.JACK_O_LANTERN_BLACK).withRarity(CosmeticRarity.EPIC)),
    JACK_O_LANTERN_WHITE_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "jack_o_lantern_white")
            .withHead(Head.JACK_O_LANTERN_WHITE).withRarity(CosmeticRarity.EPIC)),
    JACK_O_LANTERN_BROWN_BALLOON(new CosmeticInfoBuilder(Default.BALLOON, "jack_o_lantern_brown")
            .withHead(Head.JACK_O_LANTERN_BROWN).withRarity(CosmeticRarity.EPIC)),
    ;

    private static final Map<CosmeticCategory, List<Cosmetic>> cosmetics = new HashMap<>();
    static {
        for (Cosmetic cosmetic : Cosmetic.values()) {
            cosmetic.getInfo().setCosmetic(cosmetic);
            if (!cosmetics.containsKey(cosmetic.getInfo().getCategory())) {
                cosmetics.put(cosmetic.getInfo().getCategory(), new ArrayList<>());
            }
            cosmetics.get(cosmetic.getInfo().getCategory()).add(cosmetic);
        }
    }

    private final CosmeticInfo info;

    Cosmetic(CosmeticInfo info) {
        this.info = info;
    }

    public CosmeticInfo getInfo() {
        return info;
    }

    public static List<CosmeticCategory> getCategories() {
        return new ArrayList<>(cosmetics.keySet());
    }

    public static Map<CosmeticCategory, List<Cosmetic>> getCosmetics() {
        return cosmetics;
    }

    //TODO: Move this to lobby
    public ItemStack toItemStack(final Player player, boolean withAction) {
        final CosmeticUser user = HyriCosmetics.get().getUserProvider().getUser(player);
        final IHyriPlayer hyriPlayer = user.asHyriPlayer();
        final CosmeticInfo info = this.getInfo();
        final CosmeticCategory category = info.getCategory();

        final ItemBuilder builder = new ItemBuilder(user.hasUnlockedCosmetic(this) ? info.getIcon() : new ItemStack(Material.INK_SACK, 1, (short) 8))
                .withName(info.getTranslatedName().getValue(player))
                .withLore(StringUtil.splitIntoPhrases(info.getTranslatedDescription().getValue(player), 35))
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

    private String getRarityInfo(final Player player) {
        final String rarityColor = getInfo().getRarity().getColor().toString();
        final String rarityName = HyriChatColor.BOLD + getInfo().getRarity().getTranslatedName(player).toUpperCase();
        final String rarityLabel = getTranslation(player, "gui.cosmetic.rarity") + ": ";
        return rarityLabel + rarityColor + rarityName;
    }

    private String getUnlockInfo(final Player player, final IHyriPlayer hyriPlayer, ItemBuilder builder) {
        String footer;
        if (getInfo().isBuyable() && getInfo().canBuyIt(player)) {
            final String priceInfo = getPriceInfo(player, hyriPlayer, builder);
            builder.appendLore(priceInfo);
            footer = getTranslation(player, "gui.cosmetic.click_to_buy");
        } else {
            footer = getTranslation(player, "gui.cosmetic.cant_unlock");
        }
        return footer;
    }

    private String getPriceInfo(final Player player, final IHyriPlayer hyriPlayer, ItemBuilder builder) {
        String priceInfo = "";
        if (getInfo().isBuyable()) {
            builder.appendLore("");
            if (getInfo().getHyodesPrice() > 0 && getInfo().getHyrisPrice() > 0) {
                priceInfo = getTranslation(player, "gui.cosmetic.price_two_option")
                        .replace("%price1%", hyriPlayer.getHyris().getColor().toString() + info.getHyrisPrice() + " ⛃")
                        .replace("%price2%", hyriPlayer.getHyodes().getColor().toString() + info.getHyodesPrice() + " ✦");
            } else if (getInfo().getHyrisPrice() > 0) {
                priceInfo = getTranslation(player, "gui.cosmetic.price_one_option")
                        .replace("%price1%", hyriPlayer.getHyris().getColor().toString() + info.getHyrisPrice() + " ⛃");
            } else if (getInfo().getHyodesPrice() > 0) {
                priceInfo = getTranslation(player, "gui.cosmetic.price_one_option")
                        .replace("%price1%", hyriPlayer.getHyodes().getColor().toString() + info.getHyodesPrice() + " ✦");
            }
        }
        if (!info.isRequireRank() && info.getRank() != null && info.getRank() != PlayerRank.PLAYER) {
            builder.appendLore(getTranslation(player, "gui.cosmetic.offered_with_rank").replace("%rank%", info.getRank().getDefaultPrefix()));
        }
        return priceInfo;
    }

    private String getEquipInfo(final Player player, final CosmeticUser user, final CosmeticCategory category, ItemBuilder builder) {
        String footer;
        if (user.hasEquippedCosmetic(category) && user.getEquippedCosmetic(category) == this) {
            if (!user.getEquippedCosmetics().get(category).getAbstractCosmetic().hasVariants()) {
                footer = getTranslation(player, "gui.cosmetic.already_equipped");
            } else {
                footer = getTranslation(player, "gui.cosmetic.click_to_edit");
            }
            builder.withGlow();
        } else {
            footer = getTranslation(player, "gui.cosmetic.click_to_equip");
        }
        return footer;
    }

    private String getTranslation(Player player, String key) {
        return HyriLanguageMessage.get(key).getValue(player);
    }

}
