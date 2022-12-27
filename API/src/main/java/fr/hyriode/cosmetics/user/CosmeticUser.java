package fr.hyriode.cosmetics.user;

import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.hyrame.game.HyriGamePlayer;

public interface CosmeticUser {
    void equipCosmetic(AbstractCosmetic cosmetic);

    HyriGamePlayer getHyriPlayer();
}
