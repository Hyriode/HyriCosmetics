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

    private final String keyName;

    CosmeticsRarity(String keyName) {
        this.keyName = keyName;
    }

    public HyriLanguageMessage getKeyName() {
        return HyriLanguageMessage.get("");
    }
}