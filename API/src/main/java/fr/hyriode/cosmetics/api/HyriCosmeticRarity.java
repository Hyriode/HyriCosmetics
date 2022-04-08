package fr.hyriode.cosmetics.api;

/**
 * Project: HyriCosmetics
 * Created by Akkashi
 * on 01/04/2022 at 20:58
 */
public enum HyriCosmeticRarity {

    COMMON("common"),
    RARE("rare"),
    EPIC("epic"),
    LEGENDARY("legendary"),
    ;

    private final String name;

    HyriCosmeticRarity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
