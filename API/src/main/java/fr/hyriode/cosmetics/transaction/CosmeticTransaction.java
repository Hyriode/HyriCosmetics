package fr.hyriode.cosmetics.transaction;

import fr.hyriode.api.mongodb.MongoDocument;
import fr.hyriode.api.player.model.IHyriTransactionContent;

public class CosmeticTransaction implements IHyriTransactionContent {

    public static final String TYPE = "cosmetic";

    private String cosmeticId;

    public CosmeticTransaction(String cosmeticId) {
        this.cosmeticId = cosmeticId;
    }

    public CosmeticTransaction() {
    }

    public String getCosmeticId() {
        return cosmeticId;
    }

    @Override
    public void save(MongoDocument document) {
        document.append("id", this.cosmeticId);
    }

    @Override
    public void load(MongoDocument document) {
        this.cosmeticId = document.getString("id");
    }
}
