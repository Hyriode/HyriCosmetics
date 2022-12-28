package fr.hyriode.cosmetics.user;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.hyrame.game.HyriGamePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CosmeticUserImpl implements CosmeticUser {

    private final HyriGamePlayer player;
    private final Map<CosmeticCategory, PlayerCosmetic<?>> cosmetics;

    public CosmeticUserImpl(HyriGamePlayer player) {
        this.player = player;
        this.cosmetics = new HashMap<>();
    }

    @Override
    public void equipCosmetic(AbstractCosmetic cosmetic) {
        Objects.requireNonNull(cosmetic, "cosmetic must not be null");

        if (this.cosmetics.containsKey(cosmetic.getCategory())) {
            this.cosmetics.get(cosmetic.getCategory()).unequip();
            this.cosmetics.remove(cosmetic.getCategory());
        }

        this.cosmetics.put(cosmetic.getCategory(), new PlayerCosmeticImpl<>(cosmetic, this));
    }

    @Override
    public void unequipCosmetic(CosmeticCategory category) {
        Objects.requireNonNull(category, "category must not be null");

        if (this.cosmetics.containsKey(category)) {
            this.cosmetics.get(category).unequip();
            this.cosmetics.remove(category);
        }
    }

    @Override
    public PlayerCosmetic<?> getCosmetic(CosmeticCategory category) {
        Objects.requireNonNull(category, "category must not be null");

        return this.cosmetics.get(category);
    }

    @Override
    public Map<CosmeticCategory, PlayerCosmetic<?>> getCosmetics() {
        return this.cosmetics;
    }

    @Override
    public HyriGamePlayer getHyriPlayer() {
        return this.player;
    }

}