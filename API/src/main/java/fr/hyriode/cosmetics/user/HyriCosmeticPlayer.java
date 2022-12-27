package fr.hyriode.cosmetics.user;

import fr.hyriode.cosmetics.HyriCosmetics;
import fr.hyriode.cosmetics.common.AbstractCosmetic;
import fr.hyriode.cosmetics.common.CosmeticCategory;
import fr.hyriode.hyrame.game.HyriGame;
import fr.hyriode.hyrame.game.HyriGamePlayer;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Objects;

public class HyriCosmeticPlayer extends HyriGamePlayer {

    private final CosmeticUser user;

    public HyriCosmeticPlayer(HyriGame<?> game, Player player) {
        super(game, player);
        this.user = HyriCosmetics.get().getUserManager().getUser(this);
    }

    public CosmeticUser getUser() {
        return this.user;
    }
}
