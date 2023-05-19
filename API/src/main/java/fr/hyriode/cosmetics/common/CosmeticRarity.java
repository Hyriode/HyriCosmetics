package fr.hyriode.cosmetics.common;

import fr.hyriode.api.color.HyriChatColor;
import fr.hyriode.api.language.HyriLanguageMessage;
import org.bukkit.entity.Player;

import java.util.Comparator;
import java.util.List;

public enum CosmeticRarity {

    COMMON("common",  HyriChatColor.WHITE),
    RARE("rare", HyriChatColor.BLUE),
    EPIC("epic", HyriChatColor.DARK_PURPLE),
    LEGENDARY("legendary", HyriChatColor.GOLD),
    EXCLUSIVE("exclusive", HyriChatColor.LIGHT_PURPLE),
    STAFF("staff", HyriChatColor.RED)
    ;

    private final String name;
    private final HyriChatColor color;

    CosmeticRarity(String name, HyriChatColor color) {
        this.name = name;
        this.color = color;
    }

    public HyriChatColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String getTranslatedName(final Player player) {
        return HyriLanguageMessage.get("cosmetics.rarity." + this.name).getValue(player);
    }

    public static void sortCosmeticsByRarity(List<CosmeticInfo> cosmetics) {
        cosmetics.sort(Comparator.comparing(CosmeticInfo::getRarity));
    }
}