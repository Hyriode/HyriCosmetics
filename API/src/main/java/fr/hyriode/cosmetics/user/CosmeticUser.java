package fr.hyriode.cosmetics.user;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.hyrame.game.HyriGamePlayer;

import java.util.Map;

public interface CosmeticUser {
    void equipCosmetic(AbstractCosmetic cosmetic);

    void unequipCosmetic(CosmeticCategory category);

    PlayerCosmetic<?> getCosmetic(CosmeticCategory category);

    Map<CosmeticCategory, PlayerCosmetic<?>> getCosmetics();

    HyriGamePlayer getHyriPlayer();
}
