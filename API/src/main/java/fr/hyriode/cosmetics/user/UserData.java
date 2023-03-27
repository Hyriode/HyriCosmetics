package fr.hyriode.cosmetics.user;

import fr.hyriode.api.mongodb.MongoDocument;
import fr.hyriode.api.player.model.IHyriPlayerData;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class UserData implements IHyriPlayerData {

    private final Map<String, String> equippedCosmetics = new HashMap<>();

    public Map<String, String> getEquippedCosmetics() {
        return equippedCosmetics;
    }

    public UserData() {
    }

    @Override
    public void save(MongoDocument document) {
        for (Map.Entry<String, String> entry : this.equippedCosmetics.entrySet()) {
            document.append(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void load(MongoDocument document) {
        for (Map.Entry<String, Object> entry : document.entrySet()) {
            final String string = (String) entry.getValue();
            this.equippedCosmetics.put(String.valueOf(entry), string);
        }
    }
}
