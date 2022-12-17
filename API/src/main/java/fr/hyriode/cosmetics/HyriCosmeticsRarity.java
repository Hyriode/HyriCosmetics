package fr.hyriode.cosmetics;

import fr.hyriode.api.language.HyriLanguageMessage;

public enum HyriCosmeticsRarity {

    COMMON("common"),
    UNCOMMON("uncommon"),
    RARE("rare"),
    EPIC("epic"),
    LEGENDARY("legendary"),
    MYTHIC("mythic"),
    SUPREME("supreme");

    private final String keyName;

    HyriCosmeticsRarity(String keyName) {
        this.keyName = keyName;
    }

    public HyriLanguageMessage getKeyName() {
        return HyriLanguageMessage.get("");
    }
}