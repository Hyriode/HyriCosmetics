package fr.hyriode.cosmetics.common;

import fr.hyriode.api.color.HyriChatColor;

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

}