package fr.hyriode.cosmetics.common;

import fr.hyriode.api.language.HyriLanguageMessage;

public enum CosmeticsRarity {

    COMMON("common"),
    UNCOMMON("uncommon"),
    RARE("rare"),
    EPIC("epic"),
    LEGENDARY("legendary"),
    MYTHIC("mythic"),
    SUPREME("supreme");

    private final String id;

    CosmeticsRarity(final String id) {
        this.id = id;
    }

    public HyriLanguageMessage getName() {
        return HyriLanguageMessage.get("cosmetics.rarity." + this.id + ".name");
    }
}