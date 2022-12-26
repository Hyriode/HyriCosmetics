package fr.hyriode.cosmetics.common;

public enum CosmeticRarity {

    COMMON("common"),
    RARE("rare"),
    EPIC("epic"),
    LEGENDARY("legendary"),
    ;

    private final String name;

    CosmeticRarity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}