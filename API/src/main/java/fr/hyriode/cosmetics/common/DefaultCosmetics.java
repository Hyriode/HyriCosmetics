package fr.hyriode.cosmetics.common;

import fr.hyriode.api.rank.PlayerRank;
import fr.hyriode.api.rank.StaffRank;
import fr.hyriode.cosmetics.utils.Head;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

import static fr.hyriode.cosmetics.common.CosmeticCategory.Default;

/**
 * Created by AstFaster
 * on 19/05/2023 at 14:08
 */
public class DefaultCosmetics {

    public static final Set<CosmeticInfo> BALLOONS = new HashSet<>();

    // == Particles ==
    public static final CosmeticInfo ENCHANTED = new CosmeticInfoBuilder(Default.PARTICLE, "enchanted")
            .withRarity(CosmeticRarity.COMMON)
            .withIcon(Material.BOOK_AND_QUILL);
    public static final CosmeticInfo FIRE_INVOCATION = new CosmeticInfoBuilder(Default.PARTICLE, "fire_invocation")
            .withRarity(CosmeticRarity.RARE)
            .withIcon(Material.LAVA_BUCKET);
    public static final CosmeticInfo GEM_INVOCATION = new CosmeticInfoBuilder(Default.PARTICLE, "gem_invocation")
            .withRarity(CosmeticRarity.RARE)
            .withIcon(Material.EMERALD);
    public static final CosmeticInfo PORTAL_INVOCATION = new CosmeticInfoBuilder(Default.PARTICLE, "portal_invocation")
            .withRarity(CosmeticRarity.RARE)
            .withIcon(Material.ENCHANTMENT_TABLE);
    public static final CosmeticInfo STEP_IN_THE_AIR = new CosmeticInfoBuilder(Default.PARTICLE, "step_in_the_air")
            .withRarity(CosmeticRarity.EPIC)
            .withRank(PlayerRank.VIP_PLUS)
            .withIcon(Material.FEATHER);
    public static final CosmeticInfo RAINBOW_TWINS = new CosmeticInfoBuilder(Default.PARTICLE, "rainbow_twins")
            .withRarity(CosmeticRarity.LEGENDARY)
            .withRank(PlayerRank.EPIC)
            .withIcon(new ItemStack(Material.INK_SACK, 1, (short) 2));
    public static final CosmeticInfo BLACK_VORTEX = new CosmeticInfoBuilder(Default.PARTICLE, "black_vortex")
            .withRarity(CosmeticRarity.STAFF)
            .withRank(StaffRank.ADMINISTRATOR).onlyWithRank()
            .withIcon(Material.RECORD_11);


    // == Pets ==
    public static final CosmeticInfo MINI_ME = new CosmeticInfoBuilder(Default.PET, "mini_me")
            .withRarity(CosmeticRarity.COMMON)
            .withIcon(new ItemBuilder(Material.SKULL_ITEM)
                    .withData((short) 3).build());
    public static final CosmeticInfo SNOWMAN = new CosmeticInfoBuilder(Default.PET, "snowman")
            .withRarity(CosmeticRarity.RARE)
            .withRank(PlayerRank.VIP_PLUS)
            .withIcon(Head.SNOWMAN_HEAD);
    public static final CosmeticInfo REAPER = new CosmeticInfoBuilder(Default.PET, "reaper")
            .withRarity(CosmeticRarity.EPIC)
            .withRank(PlayerRank.VIP_PLUS)
            .withIcon(Head.REAPER);
    public static final CosmeticInfo GHOST = new CosmeticInfoBuilder(Default.PET, "ghost")
            .withRarity(CosmeticRarity.EPIC)
            .withRank(PlayerRank.VIP_PLUS)
            .withIcon(Head.GHOST);


    // == Balloons ==
    public static final CosmeticInfo YELLOW_BALLOON = new LevelingBalloon("yellow")
            .withIcon(Head.BALLOON_COLOR_YELLOW);
    public static final CosmeticInfo RED_BALLOON = new LevelingBalloon("red")
            .withIcon(Head.BALLOON_COLOR_RED);
    public static final CosmeticInfo BLUE_BALLOON = new LevelingBalloon("blue")
            .withIcon(Head.BALLOON_COLOR_BLUE);
    public static final CosmeticInfo GREEN_BALLOON = new LevelingBalloon("green")
            .withIcon(Head.BALLOON_COLOR_GREEN);
    public static final CosmeticInfo PURPLE_BALLOON = new LevelingBalloon("purple")
            .withIcon(Head.BALLOON_COLOR_PURPLE);
    public static final CosmeticInfo ORANGE_BALLOON = new LevelingBalloon("orange")
            .withIcon(Head.BALLOON_COLOR_ORANGE);
    public static final CosmeticInfo PINK_BALLOON = new LevelingBalloon("pink")
            .withIcon(Head.BALLOON_COLOR_PINK);
    public static final CosmeticInfo LIGHT_BLUE_BALLOON = new LevelingBalloon("light_blue")
            .withIcon(Head.BALLOON_COLOR_LIGHT_BLUE);
    public static final CosmeticInfo LIME_BALLOON = new LevelingBalloon("lime")
            .withIcon(Head.BALLOON_COLOR_LIME);
    public static final CosmeticInfo GRAY_BALLOON = new LevelingBalloon("gray")
            .withIcon(Head.BALLOON_COLOR_GRAY);
    public static final CosmeticInfo DARK_BLUE_BALLOON = new LevelingBalloon("dark_blue")
            .withIcon(Head.BALLOON_COLOR_DARK_BLUE);

    public static final CosmeticInfo HYRIODE_BALLOON = new Balloon("hyriode")
            .notPurchasable()
            .withIcon(Head.HYRIODE)
            .withRarity(CosmeticRarity.EXCLUSIVE);
    public static final CosmeticInfo TWITCH_BALLOON = new Balloon("twitch")
            .notPurchasable()
            .withIcon(Head.TWITCH)
            .withRarity(CosmeticRarity.RARE);
    public static final CosmeticInfo YOUTUBE_BALLOON = new Balloon("youtube")
            .notPurchasable()
            .withIcon(Head.YOUTUBE)
            .withRarity(CosmeticRarity.RARE);
    public static final CosmeticInfo TIKTOK_BALLOON = new Balloon("tiktok")
            .notPurchasable()
            .withIcon(Head.TIKTOK)
            .withRarity(CosmeticRarity.RARE);
    public static final CosmeticInfo TWITTER_BALLOON = new Balloon("twitter")
            .notPurchasable()
            .withIcon(Head.TWITTER)
            .withRarity(CosmeticRarity.RARE);


    public static final CosmeticInfo BEACH_BALLOON = new Balloon("beach")
            .withIcon(Head.BEACH)
            .withRarity(CosmeticRarity.RARE);
    public static final CosmeticInfo AMONG_US_BALLOON = new Balloon("among_us")
            .withIcon(Head.AMONG_US)
            .withRarity(CosmeticRarity.LEGENDARY);

    public static final CosmeticInfo PRESENT_WHITE_BALLOON = new Balloon("present_white")
            .withIcon(Head.PRESENT_WHITE)
            .withRarity(CosmeticRarity.RARE);
    public static final CosmeticInfo PRESENT_GREEN_BALLOON = new Balloon("present_green")
            .withIcon(Head.PRESENT_GREEN)
            .withRarity(CosmeticRarity.RARE);
    public static final CosmeticInfo PRESENT_BLUE_BALLOON = new Balloon("present_blue")
            .withIcon(Head.PRESENT_BLUE)
            .withRarity(CosmeticRarity.RARE);
    public static final CosmeticInfo PRESENT_PURPLE_BALLOON = new Balloon("present_purple").
            withIcon(Head.PRESENT_PURPLE)
            .withRarity(CosmeticRarity.RARE);
    public static final CosmeticInfo PRESENT_YELLOW_BALLOON = new Balloon("present_yellow")
            .withIcon(Head.PRESENT_YELLOW)
            .withRarity(CosmeticRarity.RARE);

    public static final CosmeticInfo MOON_FULL_BALLOON = new Balloon("moon_full")
            .withIcon(Head.MOON_FULL)
            .withRarity(CosmeticRarity.RARE);
    public static final CosmeticInfo MOON_HALF_BALLOON = new Balloon("moon_half")
            .withIcon(Head.MOON_HALF)
            .withRarity(CosmeticRarity.RARE);
    public static final CosmeticInfo MOON_NEW_BALLOON = new Balloon("moon_new")
            .withIcon(Head.MOON_NEW)
            .withRarity(CosmeticRarity.RARE);
    public static final CosmeticInfo SUN_BALLOON = new Balloon("sun")
            .withIcon(Head.SUN)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo RUBIKS_CUBE_BALLOON = new Balloon("rubiks_cube")
            .withIcon(Head.RUBIKS_CUBE)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo TOTEM_BALLOON = new Balloon("totem")
            .withIcon(Head.TOTEM)
            .withRarity(CosmeticRarity.LEGENDARY);
    public static final CosmeticInfo POOP_BALLOON = new Balloon("poop")
            .withIcon(Head.POOP)
            .withRarity(CosmeticRarity.LEGENDARY);

    public static final CosmeticInfo JACK_O_LANTERN_YELLOW_BALLOON = new Balloon("jack_o_lantern_yellow")
            .withIcon(Head.JACK_O_LANTERN_YELLOW)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo JACK_O_LANTERN_LIGHT_BLUE_BALLOON = new Balloon("jack_o_lantern_light_blue")
            .withIcon(Head.JACK_O_LANTERN_LIGHT_BLUE)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo JACK_O_LANTERN_BLUE_BALLOON = new Balloon("jack_o_lantern_blue")
            .withIcon(Head.JACK_O_LANTERN_BLUE)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo JACK_O_LANTERN_LIME_BALLOON = new Balloon("jack_o_lantern_lime")
            .withIcon(Head.JACK_O_LANTERN_LIME)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo JACK_O_LANTERN_GREEN_BALLOON = new Balloon("jack_o_lantern_green")
            .withIcon(Head.JACK_O_LANTERN_GREEN)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo JACK_O_LANTERN_ORANGE_BALLOON = new Balloon("jack_o_lantern_orange")
            .withIcon(Head.JACK_O_LANTERN_ORANGE)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo JACK_O_LANTERN_PINK_BALLOON = new Balloon("jack_o_lantern_pink")
            .withIcon(Head.JACK_O_LANTERN_PINK)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo JACK_O_LANTERN_PURPLE_BALLOON = new Balloon("jack_o_lantern_purple")
            .withIcon(Head.JACK_O_LANTERN_PURPLE)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo JACK_O_LANTERN_RED_BALLOON = new Balloon("jack_o_lantern_red")
            .withIcon(Head.JACK_O_LANTERN_RED)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo JACK_O_LANTERN_GRAY_BALLOON = new Balloon("jack_o_lantern_gray")
            .withIcon(Head.JACK_O_LANTERN_GRAY)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo JACK_O_LANTERN_CYAN_BALLOON = new Balloon("jack_o_lantern_cyan")
            .withIcon(Head.JACK_O_LANTERN_CYAN)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo JACK_O_LANTERN_MAGENTA_BALLOON = new Balloon("jack_o_lantern_magenta")
            .withIcon(Head.JACK_O_LANTERN_MAGENTA)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo JACK_O_LANTERN_BLACK_BALLOON = new Balloon("jack_o_lantern_black")
            .withIcon(Head.JACK_O_LANTERN_BLACK)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo JACK_O_LANTERN_WHITE_BALLOON = new Balloon("jack_o_lantern_white")
            .withIcon(Head.JACK_O_LANTERN_WHITE)
            .withRarity(CosmeticRarity.EPIC);
    public static final CosmeticInfo JACK_O_LANTERN_BROWN_BALLOON = new Balloon("jack_o_lantern_brown")
            .withIcon(Head.JACK_O_LANTERN_BROWN)
            .withRarity(CosmeticRarity.EPIC);


    private static class LevelingBalloon extends Balloon {

        public LevelingBalloon(String id) {
            super(id);
            this.withRarity(CosmeticRarity.COMMON);
            this.notPurchasable();
        }

    }

    private static class Balloon extends CosmeticInfoBuilder {

        public Balloon(String id) {
            super(Default.BALLOON, id);
            
            BALLOONS.add(this);
        }

    }

}
