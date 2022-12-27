package fr.hyriode.cosmetics.user;

import fr.hyriode.hyrame.game.HyriGamePlayer;

public interface CosmeticUserProvider {

    CosmeticUser getUser(final HyriGamePlayer player);

}
