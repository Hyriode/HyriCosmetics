package fr.hyriode.cosmetics.transaction;

import fr.hyriode.api.transaction.IHyriTransactionContent;

public class CosmeticTransaction implements IHyriTransactionContent {

    public static final String TYPE = "cosmetic";

    private final String cosmeticId;

    public CosmeticTransaction(String cosmeticId) {
        this.cosmeticId = cosmeticId;
    }

    public String getCosmeticId() {
        return cosmeticId;
    }
}
