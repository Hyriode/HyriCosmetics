package fr.hyriode.cosmetics.user;

import fr.hyriode.hyrame.game.HyriGamePlayer;

import java.util.HashMap;
import java.util.Map;

public class CosmeticUserProviderImpl implements CosmeticUserProvider {

    private final Map<HyriGamePlayer, CosmeticUser> users = new HashMap<>();

    @Override
    public CosmeticUser getUser(HyriGamePlayer player) {
        return this.users.computeIfAbsent(player, CosmeticUserImpl::new);
    }
}
