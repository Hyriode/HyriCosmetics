package fr.hyriode.cosmetics.common;

import fr.hyriode.api.language.HyriLanguageMessage;
import fr.hyriode.api.rank.IHyriRankType;
import fr.hyriode.api.rank.PlayerRank;
import fr.hyriode.cosmetics.common.CosmeticCategory.Default;
import fr.hyriode.cosmetics.utils.Head;
import fr.hyriode.hyrame.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Cosmetic {

    // == Particles ==
    ENCHANTED(Default.PARTICLE, "enchanted", CosmeticRarity.RARE, PlayerRank.PLAYER, 15000, 15000, new ItemBuilder(Material.BOOK_AND_QUILL).build()),
    FIRE_INVOCATION(Default.PARTICLE, "fire_invocation", CosmeticRarity.LEGENDARY, PlayerRank.PLAYER, 150, 15000, new ItemBuilder(Material.LAVA_BUCKET).build()),
    STEP_IN_THE_AIR(Default.PARTICLE, "step_in_the_air", CosmeticRarity.EPIC, PlayerRank.VIP, -1, -1, new ItemBuilder(Material.FEATHER).build()),
    RAINBOW_TWINS(Default.PARTICLE, "rainbow_twins", CosmeticRarity.COMMON, PlayerRank.PLAYER, 150, 15000, new ItemBuilder(Material.INK_SACK, 1, 2).build()),

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
    private final IHyriRankType requireRank;
    private final int hyodesPrice;
    private final int hyrisPrice;
    private final ItemStack icon;

    Cosmetic(CosmeticCategory category, String id, CosmeticRarity rarity, IHyriRankType requireRank, int hyodesPrice, int hyrisPrice, ItemStack icon) {
        this.category = category;
        this.id = id;
        this.rarity = rarity;
        this.requireRank = requireRank;
        this.hyodesPrice = hyodesPrice;
        this.hyrisPrice = hyrisPrice;
        this.icon = icon;
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

    public IHyriRankType getRequireRank() {
        return requireRank;
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
}
