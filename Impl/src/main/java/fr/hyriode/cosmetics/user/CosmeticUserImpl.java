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
    public void equipCosmetic(AbstractCosmetic cosmetic, Class<? extends AbstractCosmetic> clazz) {
        Objects.requireNonNull(cosmetic, "cosmetic must not be null");

        this.cosmetics.put(cosmetic.getCategory(), new PlayerCosmeticImpl<>(cosmetic, ));
    }

    @Override
    public HyriGamePlayer getHyriPlayer() {
        return this.player;
    }

}