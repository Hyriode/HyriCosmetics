package fr.hyriode.cosmetics.user;

import fr.hyriode.api.mongodb.MongoDocument;
import fr.hyriode.api.mongodb.MongoSerializable;
import fr.hyriode.api.mongodb.MongoSerializer;
import fr.hyriode.api.player.model.IHyriPlayerData;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.cosmetics.common.Filters.Owned;
import fr.hyriode.cosmetics.common.Filters.Price;
import fr.hyriode.cosmetics.common.Filters.Rarity;
import fr.hyriode.hyrame.utils.Pair;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class UserData implements IHyriPlayerData {

    private final Map<String, Pair<String, String >> equippedCosmetics = new HashMap<>();
    private FilterData filters = new FilterData();

    private CosmeticUser user;

    public UserData() {
    }

    @Override
    public void save(MongoDocument document) {
        final MongoDocument equippedDocument = new MongoDocument();

        for (Map.Entry<CosmeticCategory, PlayerCosmetic<?>> entry : this.user.getEquippedCosmetics().entrySet()) {
            final PlayerCosmetic<?> cosmetic = entry.getValue();

            if (cosmetic.getAbstractCosmetic().hasVariants()) {
                final MongoDocument cosmeticDocument = new MongoDocument();

                cosmeticDocument.append(entry.getValue().getAbstractCosmetic().getInfo().getId(), cosmetic.getAbstractCosmetic().getVariant());
                equippedDocument.append(entry.getKey().getName(), cosmeticDocument);
            } else {
                equippedDocument.append(entry.getKey().getName(), entry.getValue().getAbstractCosmetic().getInfo().getId());
            }
        }

        document.append("equipped", equippedDocument);
        document.append("filters", MongoSerializer.serialize(this.filters));
    }

    @Override
    public void load(MongoDocument document) {
        if (document.containsKey("equipped")) {
            for (Map.Entry<String, Object> entry : MongoDocument.of((Document) document.get("equipped")).entrySet()) {
                if (entry.getValue() instanceof Document) {
                    final Document cosmeticDocument = (Document) entry.getValue();
                    for (Map.Entry<String, Object> cosmeticEntry : cosmeticDocument.entrySet()) {
                        this.equippedCosmetics.put(entry.getKey(), new Pair<>(cosmeticEntry.getKey(), (String) cosmeticEntry.getValue()));
                    }
                } else {
                    this.equippedCosmetics.put(entry.getKey(), new Pair<>((String) entry.getValue(), ""));
                }
            }
        }

        final FilterData filterData = new FilterData();

        if (!document.containsKey("filters")) {
            this.filters = filterData;
            return;
        }

        filterData.load(MongoDocument.of((Document) document.get("filters")));

        this.filters = filterData;
    }

    public Map<String, Pair<String, String>> getEquippedCosmetics() {
        return equippedCosmetics;
    }

    public void putEquippedCosmetics(String key, String cosmeticId, String variant) {
        this.equippedCosmetics.put(key, new Pair<>(cosmeticId, variant));
    }

    public void putEquippedCosmetics(String key, String cosmeticId) {
        this.equippedCosmetics.put(key, new Pair<>(cosmeticId, ""));
    }

    public FilterData getFilters() {
        return filters;
    }

    public void setUser(CosmeticUser user) {
        this.user = user;
    }

    public static class FilterData implements MongoSerializable {

        private Owned owned = Owned.ALL;
        private Rarity rarity = Rarity.NO;
        private Price price = Price.NO;

        @Override
        public void save(MongoDocument document) {
            document.append("owned", this.owned.getName());
            document.append("rarity", this.rarity.getName());
            document.append("price", this.price.getName());
        }

        @Override
        public void load(MongoDocument document) {
            this.owned = document.getString("owned") != null ? Owned.getByName(document.getString("owned")) : Owned.ALL;
            this.rarity = document.getString("rarity") != null ? Rarity.getByName(document.getString("rarity")) : Rarity.NO;
            this.price = document.getString("price") != null ? Price.getByName(document.getString("price")) : Price.NO;
        }

        public Owned getOwned() {
            return owned;
        }

        public void setOwned(Owned owned) {
            this.owned = owned;
        }

        public Rarity getRarity() {
            return rarity;
        }

        public void setRarity(Rarity rarity) {
            this.rarity = rarity;
        }

        public Price getPrice() {
            return price;
        }

        public void setPrice(Price price) {
            this.price = price;
        }
    }
}
